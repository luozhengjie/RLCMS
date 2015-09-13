package com.rl.cms.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class UserNamePasswordRealm implements Realm {

	private JdbcTemplate template;

	private AesCipherService aesCipherService = new AesCipherService();

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public String getName() {
		return "rlrealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		try {
			UsernamePasswordToken upt = (UsernamePasswordToken) token;
			String username = upt.getUsername();
			char[] password = upt.getPassword();
			List<SecurityUserEntity> lists = template.query(
					"select * from  security_user where username=?",
					new Object[] { username }, new SecurityUserEntity());
			if (lists != null && lists.size() > 0) {
				SecurityUserEntity entity = lists.get(0);
				byte[] keyBytes = entity.getKey();

				if (new String(password).equals(new String(Base64
						.decode(aesCipherService.decrypt(
								Base64.decode(entity.getPassword()), keyBytes)
								.toBase64())))) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		List<Map<String, Object>> list =  new ArrayList<Map<String, Object>>();
		try {
			UsernamePasswordToken upt = (UsernamePasswordToken) token;
			String username = upt.getUsername();
			list = template
					.queryForList(
							"select srp.permission as permission from  security_user_roles as sur , security_roles_permissions as srp where sur.username=? and srp.role_name = sur.role_name",
							username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RLCMSAuthenticationInfo(this.getName(), list,
				token.getCredentials());
	}

}

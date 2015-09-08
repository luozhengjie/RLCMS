package com.rl.cms.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

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
			UsernamePasswordToken upt = (UsernamePasswordToken) token
					.getCredentials();
			String username = upt.getUsername();
			char[] password = upt.getPassword();
			SecurityUserEntity entity = template.queryForObject(
					"select * from  security_user where username=?",
					SecurityUserEntity.class, username);
			if (entity != null) {

				if (entity.getPassword().equals(
						aesCipherService.encrypt(
								ByteSource.Util.bytes(password).getBytes(),
								ByteSource.Util.bytes(entity.getKey())
										.getBytes()).toHex())) {
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
		// TODO Auto-generated method stub
		return null;
	}

}

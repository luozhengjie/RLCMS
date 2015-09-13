package com.rl.cms.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.PermissionResolverAware;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.RolePermissionResolverAware;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.Initializable;

public class RLCMSAuthenticationInfo extends AuthenticatingRealm
implements Authorizer, Initializable, PermissionResolverAware, RolePermissionResolverAware {

	private Object credentials;
	
	private PrincipalCollection principalCollection;
	
	public RLCMSAuthenticationInfo(String realmName, List<Map<String, Object>> permissions, Object credentials){
		this.credentials = credentials;
		SimplePrincipalCollection spc = new SimplePrincipalCollection();
		for(Map<String, Object> map : permissions){
			spc.add(map.get("permission"), realmName);
		}
		this.principalCollection = spc;
	}
	
	public PrincipalCollection getPrincipals() {
		return principalCollection;
	}

	public Object getCredentials() {
		return credentials;
	}

	@Override
	public void setRolePermissionResolver(RolePermissionResolver rpr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPermissionResolver(PermissionResolver pr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPermitted(PrincipalCollection subjectPrincipal,
			Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] isPermitted(PrincipalCollection subjectPrincipal,
			String... permissions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean[] isPermitted(PrincipalCollection subjectPrincipal,
			List<Permission> permissions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermittedAll(PrincipalCollection subjectPrincipal,
			String... permissions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPermittedAll(PrincipalCollection subjectPrincipal,
			Collection<Permission> permissions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkPermission(PrincipalCollection subjectPrincipal,
			String permission) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPermission(PrincipalCollection subjectPrincipal,
			Permission permission) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPermissions(PrincipalCollection subjectPrincipal,
			String... permissions) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPermissions(PrincipalCollection subjectPrincipal,
			Collection<Permission> permissions) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasRole(PrincipalCollection subjectPrincipal,
			String roleIdentifier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] hasRoles(PrincipalCollection subjectPrincipal,
			List<String> roleIdentifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAllRoles(PrincipalCollection subjectPrincipal,
			Collection<String> roleIdentifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkRole(PrincipalCollection subjectPrincipal,
			String roleIdentifier) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRoles(PrincipalCollection subjectPrincipal,
			Collection<String> roleIdentifiers) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRoles(PrincipalCollection subjectPrincipal,
			String... roleIdentifiers) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}

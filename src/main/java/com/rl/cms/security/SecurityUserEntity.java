package com.rl.cms.security;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SecurityUserEntity implements RowMapper{

	private Integer id;
	
	private String username;
	
	private String password;
	
	private byte[] key;

	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}





	public byte[] getKey() {
		return key;
	}



	public void setKey(byte[] key) {
		this.key = key;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SecurityUserEntity entity = new SecurityUserEntity();
		entity.setUsername(rs.getString("username"));
		entity.setPassword(rs.getString("password"));
		entity.setKey(rs.getBytes("password_key"));
		entity.setId(rs.getInt("id"));
		return entity;
	}

	
}

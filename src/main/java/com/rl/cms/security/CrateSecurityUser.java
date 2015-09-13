package com.rl.cms.security;

import java.security.Key;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.OperationMode;
import org.apache.shiro.crypto.PaddingScheme;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.StatementCallback;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class CrateSecurityUser {

	public static void main(String[] args) throws Base64DecodingException {
		AesCipherService aesCipherService = new AesCipherService();
		aesCipherService.setKeySize(128); 
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
		JdbcTemplate template = (JdbcTemplate) context.getBean("jdbcTemplate");
		Key key = aesCipherService.generateNewKey();
		final byte[] bytes = key.getEncoded();
		final String password = aesCipherService.encrypt(ByteSource.Util.bytes("123456").getBytes(), bytes).toBase64();
		
		System.out.println(password);
		System.out.println(new String(Base64.decode(aesCipherService.decrypt(Base64.decode(password), bytes).toBase64())));
		for(byte b : bytes){
			System.out.print(b+",");
		}
		template.execute("insert into security_user(password,username,password_key) values(?,?,?)", new PreparedStatementCallback(){

			@Override
			public Object doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ps.setString(1, password);
				ps.setString(2, "admin");
				ps.setBytes(3, bytes);
				ps.execute();
				return null;
			}
			
		});
	}

}

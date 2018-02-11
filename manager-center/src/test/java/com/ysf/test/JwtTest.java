package com.ysf.test;

import java.security.Key;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 * JWT测试类
 * @author sunwenxing
 */
public class JwtTest {
	public static void main(String[] args) {
		Key key = MacProvider.generateKey();	//获取随机key
		String compactJws = Jwts.builder()
				.setSubject("Joe")
				.setClaims
				(new DefaultClaims().setId(String.valueOf(IdWorker.getId())))
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();

		System.out.println(compactJws);

		assert Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject().equals("Joe");

		try {
			Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody()	;
			System.out.println(body);
			System.out.println(body.getExpiration());
		}catch(SignatureException e) {
			System.out.println("not trust");
		}catch(ExpiredJwtException e) {
			System.out.println("ExpiredJwtException");
		}
	}
}

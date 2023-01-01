package com.test.execption.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64UrlCodec;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @date 2021/1/19
 */
public class JwtUtil {

	private static final String secret = "asdfasdf";

	public static String createToken(String subject){

		String token = Jwts.builder().setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();

		return token;
	}

	public static String parseToken(String token){
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		String subject = body.getSubject();
		return subject;
	}


	static String secrest = "fdgfdfgds";
	public static void main(String[] args) throws InterruptedException {
		String str = "小明";
		String compact = Jwts.builder().setSubject(str).setExpiration(new Date(System.currentTimeMillis()+1000*5*60)).signWith(SignatureAlgorithm.HS256, secrest).compact();
		System.out.println(compact);
		Thread.sleep(6000);
		Claims body = Jwts.parser().setSigningKey(secrest).parseClaimsJws(compact).getBody();
		String subject = body.getSubject();
		System.out.println(subject);
	}



//	public static void main(String[] args) {
//		String str = "小明";
//		String compact = Jwts.builder().setSubject(str).signWith(SignatureAlgorithm.HS256, secrest).compact();
//		System.out.println(compact);
//	}





//	public static void main(String[] args) throws InterruptedException {
//		String name = "教育";
//
//
//
//		String token = createToken(name);
//		System.out.println("token:"+token);
//
//		String srcStr = parseToken(token);
//		System.out.println("解析出来："+srcStr);
//		System.out.println("==========================");
//		TimeUnit.SECONDS.sleep(4);
//		srcStr = parseToken(token);
//		System.out.println("解析出来："+srcStr);
//
//	}
}

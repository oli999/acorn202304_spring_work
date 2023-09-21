package com.example.boot10.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.CookieRequestCache;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.example.boot10.util.JwtUtil;
/*
 *  DB 에 저장된 계정 정보가 일치하면 로그인 처리를 해야한다.
 *  세션을 사용하지 않기 때문에 세션에 로그인 정보를 저장하지 않는다.
 *  클라이언트에게 Jwt 쿠키를 응답하는 것으로 로그인 처리를 대신한다.
 *  다음번 요청에 Jwt 쿠키가 같이 넘어오면 해당 쿠키를 JwtFilter 에서 확인해서 인증하면 되는 것이다. 
 */
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	//Jwt 토큰 유틸
	@Autowired 
	private JwtUtil jwtUtil;
	
	//jwt 를 쿠키로 저장할때 쿠키의 이름
	@Value("${jwt.name}")
	private String jwtName;
	//쿠키 유지시간
	@Value("${jwt.cookie.expiration}")
	private int cookieExpiration;
	
	//로그인 성공후 원래 목적지로 리다일렉트 시켜주기 위해 필요한 객체 
	@Autowired
	private CookieRequestCache requestCache;
	//세션을 사용하지 않기 때문에 CookieRequestChache 를 사용해야 한다.
	@Autowired
    public AuthSuccessHandler(CookieRequestCache requestCache) {
        super.setRequestCache(requestCache);
    }
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
    	//여기 까지 실행순서가 넘어오면 인증을 통과 했으므로 토큰을 발급해서 응답한다.
		String jwtToken=jwtUtil.generateToken(authentication.getName());
		// JWT를 쿠키에 담아 응답 (쿠키에 공백문자는 에러를 일으키기 때문에 공백대신에  "+" 를 사용함)
        Cookie cookie = new Cookie(jwtName, "Bearer+"+jwtToken);
        cookie.setMaxAge(cookieExpiration); // 쿠키 유지 시간 초 단위로 설정
        cookie.setHttpOnly(true); //웹브라우저에서 JavaScript에서 접근 불가 하도록 설정 
        cookie.setPath("/"); // 모든 경로에서 쿠키를 사용할수 있도록 설정 
        response.addCookie(cookie);
        
    	SavedRequest cashed=requestCache.getRequest(request, response);
        if(cashed==null) {
        	RequestDispatcher rd=request.getRequestDispatcher("/users/login_success");
        	rd.forward(request, response);
        }else {
        	super.onAuthenticationSuccess(request, response, authentication);
        }
      
    }
}


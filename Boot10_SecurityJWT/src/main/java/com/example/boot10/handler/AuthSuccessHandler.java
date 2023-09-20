package com.example.boot10.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.CookieRequestCache;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.example.boot10.util.JwtUtil;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Autowired JwtUtil jwtUtil;
	
	@Autowired
	private CookieRequestCache requestCache;
	
	@Autowired
    public AuthSuccessHandler(CookieRequestCache requestCache) {
        super.setRequestCache(requestCache);
    }
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
    	//여기 까지 실행순서가 넘어오면 인증을 통과 했으므로 토큰을 발급해서 응답한다.
		String jwtToken=jwtUtil.generateToken(authentication.getName());
		// JWT를 쿠키에 담아 응답
        Cookie cookie = new Cookie("jwtToken", "Bearer+"+jwtToken);
        cookie.setMaxAge(60*3); // 초 단위로 설정
        cookie.setHttpOnly(true); // JavaScript에서 접근 불가
        cookie.setPath("/"); // 모든 경로에서 쿠키 접근 가능
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


package com.example.boot09.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;


/*
 *  로그인 하지 않은 상태로 인증이 필요한 경로를 직접 요청했을경우에  로그인폼으로 자동 리다일렉트 된다.
 *  그 후에 로그인 성공을 한다면 원래 요청했던 경로로 자동 리다일렉트 되도록 하기 위해서 이 핸들러가 필요하다.
 */
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	//1. 요청캐쉬 객체를 직접 생성해서 
    private RequestCache requestCache = new HttpSessionRequestCache();
    
    //2. 생성자에서 부모객체에 전달
    public AuthSuccessHandler() {
        super.setRequestCache(requestCache);
    }
    //로그인 성공이후 자동으로 호출되는 메소드 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //세션 유지 시간 설정
    	HttpSession session=request.getSession();
        session.setMaxInactiveInterval(60*20);
        //3. 로그인 성공이후 미리 저장된 요청이 있었는지 읽어와서
    	SavedRequest cashed=requestCache.getRequest(request, response);
    	//4. 만일 미리 저장된 요청이 없다면 (로그인 하지 않은 상태로 인증이 필요한 경로를 요청하지 않았다면)
        if(cashed==null) {
        	//5. 로그인 환영 페이지로 foward 이동 
        	RequestDispatcher rd=request.getRequestDispatcher("/users/login_success");
        	rd.forward(request, response);
        }else {
        	//6. 원래 가려던 목적지 경로로 리다일렉트 이동 시킨다 (GET 방식 요청 파라미터도 자동으로 같이 가지고 간다)
        	super.onAuthenticationSuccess(request, response, authentication);
        }
      
    }
}







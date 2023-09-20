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



public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
 
    private RequestCache requestCache = new HttpSessionRequestCache();

    public AuthSuccessHandler() {
        super.setRequestCache(requestCache);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(10);
    	SavedRequest cashed=requestCache.getRequest(request, response);
        if(cashed==null) {
        	RequestDispatcher rd=request.getRequestDispatcher("/users/login_success");
        	rd.forward(request, response);
        }else {
        	super.onAuthenticationSuccess(request, response, authentication);
        }
      
    }
}







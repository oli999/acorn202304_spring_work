package com.example.boot10.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailHandler implements AuthenticationFailureHandler{
	//로그인 실패시 호출되는 메소드 
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//어떤 종류의 예외가 발생했는지 확인해서 
		String errMsg = "";
        if (exception instanceof BadCredentialsException) {
            errMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
        } else if (exception instanceof DisabledException) {
            errMsg = "계정이 비활성화되었습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof CredentialsExpiredException) {
            errMsg = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
        }
        //에러 메세지를 담은 후에 
        request.setAttribute("errMsg",  errMsg);
        //로그인 폼으로 forward 이동하면서 에러메세지를 전달해 준다. 
        request.getRequestDispatcher("/users/loginform?error").forward(request,  response);
	}
	
}










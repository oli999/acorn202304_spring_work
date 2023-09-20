package com.example.boot10.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.boot10.service.CustomUserDetailsService;
import com.example.boot10.util.JwtUtil;



@Component
public class JwtFilter extends OncePerRequestFilter{
	
	
	//JwtUtil 객체 주입 받기 
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 쿠키에서 JWT 토큰 추출
        Cookie[] cookies = request.getCookies();
        // 쿠키에 
        String jwtToken = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        }
        
        System.out.println(jwtToken);
        //사용자명 
		String userName=null;
		// 토큰 Bearer 로 시작 하는지 확인해서 
		if(jwtToken.startsWith("Bearer+")) {
			//앞에 "Bearer " 를 제외한 순수 토큰 문자열 얻어내기 
			jwtToken=jwtToken.substring(7);
			//유틸을 이용해서 토큰에 저장된 userName (subject) 를 얻어낸다
			userName=jwtUtil.extractUsername(jwtToken);
		}
		//userName 이 존재하고  Spring Security 에서 아직 인증을 받지 않은 상태라면 
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			//읽어낸 userName 을 이용해서  UserDetails 객체를 얻어낸다 
			UserDetails userDetails=service.loadUserByUsername(userName);
			//token 이 유효한 토큰인지 유틸을 이용해서 알아낸다
			boolean isValid = jwtUtil.validateToken(jwtToken, userDetails);
			if(isValid) {
				//사용자가 제출한 사용자 이름과 비밀번호와 같은 인증 자격 증명을 저장
				UsernamePasswordAuthenticationToken authToken=
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//보안 컨텍스트 업데이트 
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		//다음 필터 chain 진행하기
		filterChain.doFilter(request, response);
	}

}










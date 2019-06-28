package kh.spring.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect
public class LoginCheckAdvice {

	@Autowired
	private HttpSession session;
	
	@Around("execution(* kh.spring.practice.BoardController.*(..))")
	public Object loginCheck(ProceedingJoinPoint pjp)throws Throwable{
		if(session.getAttribute("loginId") == null) {
			System.out.println("점검중");
			ModelAndView mav = new ModelAndView();
			mav.addObject("msg", "로그인을 먼저 진행해주세요.");
			mav.setViewName("home");
			return mav;
		}else { 
				return pjp.proceed();
		}
	}
}

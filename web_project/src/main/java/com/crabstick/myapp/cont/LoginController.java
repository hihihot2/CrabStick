package com.crabstick.myapp.cont;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crabstick.myapp.login.LoginService;
import com.crabstick.myapp.login.Members;

@Controller
public class LoginController {
	@Resource(name = "loginService")
	private LoginService service;

	public void setService(LoginService service) {
		this.service = service;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	@RequestMapping(value = "/logincont/loginpage.do", method = RequestMethod.GET)
	public String loginpage() {
		System.out.println("로그인페이지로이동");
		return "login/loginform";
	}

	@RequestMapping(value = "/logincont/login.do")
	public ModelAndView login(Members m, HttpSession hs) {
		String result = null;		
		System.out.println("로그인시작");
		int chk = service.mem_login(m);
		System.out.println(chk);
		if (chk != 0) {
			System.out.println("로그인 성공");			
			//id를 세션값으로 준다.
			hs.getAttribute(m.getMem_id());
			ModelAndView mav = new ModelAndView("/main");			
			return mav;
		}else{
			System.out.println("로그인 실패");
			ModelAndView mav = new ModelAndView("/login/loginform");
			result = "fail";
			mav.addObject("result", result);
			return mav;

		}
	}

}

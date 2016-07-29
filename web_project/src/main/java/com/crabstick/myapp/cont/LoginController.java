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

	@RequestMapping(value = "/logincont/logout.do")
	public String logout(HttpSession hs) {		
		hs.invalidate();		
		return "main";
	}	
	
	@RequestMapping(value = "/logincont/login.do", method = RequestMethod.POST)
	public ModelAndView login(Members m, HttpSession hs) {
		ModelAndView mav = new ModelAndView("/login/loginchkJSON");
		System.out.println("로그인시작");
		int chk = service.mem_login(m);
		System.out.println(chk);
		if (chk != 0) {
			System.out.println("로그인 성공");			
			//num을 세션값으로 준다.
			int no = service.getmem_no(m);
			hs.setAttribute("no",no);
			mav.addObject("chk", chk);
			return mav;
		}else{
			System.out.println("로그인 실패");
			mav.addObject("chk", chk);
			return mav;

		}
	}

}

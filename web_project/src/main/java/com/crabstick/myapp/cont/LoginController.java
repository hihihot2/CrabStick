package com.crabstick.myapp.cont;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/")
	public String main() {
		return "main";
	}

	@RequestMapping(value = "/logincont/loginpage.do", method = RequestMethod.GET)
	public String loginpage() {
		System.out.println("로그인페이지로이동");
		return "login/loginform";
	}

	//로그아웃
	@RequestMapping(value = "/logincont/logout.do")
	public String logout(HttpSession hs) {		
		hs.invalidate();		
		return "main";
	}		
	//로그인시작
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
	
	@RequestMapping(value = "/logincont/joinpage.do")
	public String joinpage() {		
		return "redirect:/logincont/joinpagego.do";		
	}
	@RequestMapping(value = "/logincont/joinpagego.do")
	public String joinpagego() {		
		return "login/joinform";		
	}	
	//회원가입시 아이디 중복체크
	@RequestMapping(value="/logincont/idchk.do", method = RequestMethod.POST)
	public ModelAndView idchk(@RequestParam(value="mem_id")String mem_id) {
		System.out.println("아이디 중복체크 시작" + mem_id);		
		ModelAndView mav = new ModelAndView("login/idchkJSON");
		int chk = service.getmem_id(mem_id);
		System.out.println(chk);
		mav.addObject("chk", chk);	
		return mav;		
	}	
	@RequestMapping(value="/logincont/join.do", method = RequestMethod.POST)
	public String join(Members m) {
		System.out.println("회원가입 시작");
		service.mem_join(m);		
		System.out.println("저장완료");		
		return "redirect:/logincont/login.do";
	}	
}

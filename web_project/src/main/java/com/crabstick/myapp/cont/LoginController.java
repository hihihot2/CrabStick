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
	@RequestMapping(value = "/logincont/login.do", method = {RequestMethod.GET, RequestMethod.POST})
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
	//join페이지로 이동
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
	//회원가입 DB에 값넣기
	@RequestMapping(value="/logincont/join.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(Members m) {
		System.out.println(m.getMem_id() + m.getMem_pwd() + m.getMem_name());
		System.out.println("회원가입 시작");
		service.mem_join(m);		
		System.out.println("저장완료");		
		return "redirect:/";
	}	
	
	//mypage로 값보내기
	@RequestMapping(value="/logincont/mypage.do")
	public ModelAndView mypagego(HttpSession hs){		
		ModelAndView mav = new ModelAndView("login/mypage");
		//세션값으로 members값 가져오기
		int mem_no = (Integer) hs.getAttribute("no");
		System.out.println(mem_no);
		System.out.println("마이페이지로 가기");		
		Members members = service.getmem_all(mem_no);
		System.out.println(members.getMem_id());
		mav.addObject("members", members);		
		return mav;
	}
	
	@RequestMapping(value="/logincont/dropoutpage.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView dropoutpage(@RequestParam(value="mem_id")String mem_id){
		ModelAndView mav = new ModelAndView("login/dropoutpage");
		String id = mem_id;
		mav.addObject("mem_id", id);
		return mav;
	}
	@RequestMapping(value="/logincont/dropout.do")
	public ModelAndView dropout(Members m,HttpSession hs){
		System.out.println("삭제시작");
		ModelAndView mav = new ModelAndView("login/delchkJSON");
		int chk = service.del_memchk(m);
		if (chk != 0) {
			System.out.println("비밀번호가 같다.");
			int mem_no = (Integer) hs.getAttribute("no");
			System.out.println(mem_no);
			service.del_mem(mem_no);
			hs.invalidate();
			mav.addObject("chk", chk);
		} else {
			System.out.println("비밀번호가 다르다.");
			mav.addObject("chk", chk);
		}
		return mav;

	}

	
}

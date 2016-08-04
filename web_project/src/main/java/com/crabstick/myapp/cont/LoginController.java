package com.crabstick.myapp.cont;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crabstick.myapp.login.LoginService;
import com.crabstick.myapp.login.Member;

@Controller
public class LoginController {
	@Resource(name = "loginService")
	private LoginService service;

	public void setService(LoginService service) {
		this.service = service;
	}

	@RequestMapping(value = "/logincont/loginpage.do", method = RequestMethod.GET)
	public String loginpage() {
		System.out.println("로그인페이지로이동");
		return "login/loginform";
	}

	// 로그아웃
	@RequestMapping(value = "/logincont/logout.do")
	public String logout(HttpSession hs, HttpServletResponse rsp,
			HttpServletRequest req) {
		hs.invalidate();
		Cookie[] cookies = req.getCookies();
		for (int i = 0; i < cookies.length; i++) { // 쿠키를 반복문으로 돌린다.
			cookies[i].setMaxAge(0); // 쿠키의 유효시간을 0 으로 셋팅한다.
			rsp.addCookie(cookies[i]); // 수정한 쿠키를 응답에
			System.out.println(cookies[i].getValue());
		}	
		System.out.println("쿠키 해제됨");
		return "redirect:/";
	}

	// 로그인시작
	@RequestMapping(value = "/logincont/login.do", method = RequestMethod.POST)
	public ModelAndView login(Member m, HttpSession hs, HttpServletResponse rsp,
			HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView("/login/loginchkJSON");
		System.out.println("로그인시작");
		String isChk= req.getParameter("always_login");
		int chk = service.mem_login(m);
		System.out.println(isChk);
		if (chk != 0) {
			if (isChk.equals("auto")) {
				Cookie autoLogin = new Cookie("autoLogin", "ture");
				Cookie autoID = new Cookie("autoID", m.getMem_id());
				Cookie autoPass = new Cookie("autoPwd", m.getMem_pwd());
				autoLogin.setMaxAge(1000);
				autoID.setMaxAge(1000);
				autoPass.setMaxAge(1000);
				rsp.addCookie(autoID);
				rsp.addCookie(autoPass);
				rsp.addCookie(autoLogin);
				System.out.println(autoLogin.getValue());
				System.out.println(autoID.getValue());
				System.out.println(autoPass.getValue());
			} 
			System.out.println("로그인 성공");
			int no = service.getmem_no(m);
			hs.setAttribute("no", no); // no == 세션값
			mav.addObject("chk", chk);
		} else {
			System.out.println("로그인 실패");
			mav.addObject("chk", chk);
		}
		
		return mav;
	}

	@RequestMapping(value = "/logincont/joinpage.do")
	public String joinpage() {
		return "redirect:/survey/main.do";
	}

	@RequestMapping(value = "/logincont/joinpagego.do")
	public String joinpagego() {
		return "login/joinform";
	}

	// 회원가입시 아이디 중복체크
	@RequestMapping(value = "/logincont/idchk.do", method = RequestMethod.POST)
	public ModelAndView idchk(@RequestParam(value = "mem_id") String mem_id) {
		System.out.println("아이디 중복체크 시작" + mem_id);
		int chk = 0;
		ModelAndView mav = new ModelAndView("login/idchkJSON");
		String[] idSpl = mem_id.split("@");
		System.out.println("길이" + idSpl.length);
		System.out.println("0번째 길이" + idSpl[0].length());
		if (idSpl.length == 2 && idSpl[0].length() != 0) {
			chk = service.getmem_id(mem_id);
		} else {
			chk = 1;
		}
		mav.addObject("chk", chk);
		return mav;
	}

	@RequestMapping(value = "/logincont/join.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(Member m) {
		System.out.println("회원가입 시작");
		service.mem_join(m);
		System.out.println("저장완료");
		return "main";
	}

	@RequestMapping(value = "/logincont/findpass.do")
	public String findpass() {
		return "redirect:/logincont/findpassgo.do";
	}

	@RequestMapping(value = "/logincont/findpassgo.do")
	public String findpassgo() {
		return "login/findpass";
	}

/*	@RequestMapping(value = "/logincont/searchpass.do", method = RequestMethod.POST)
	public ModelAndView searchpass(Member m) {
		System.out.println("비밀번호찾는중");
		ModelAndView mav = new ModelAndView("login/findpassJSON");
		String pass = service.getmem_pass(m);
		if (pass == null) {
			pass = "0";
		}
		mav.addObject("pass", pass);
		System.out.println("비밀번호찾기완료");
		return mav;
	}*/
	@RequestMapping(value = "/logincont/editpass.do", method = RequestMethod.POST)
	public String searchpass(Member m) {
		System.out.println(m.toString());
		service.updatePass(m);
		System.out.println("패스워드 수정완료");
		return "main";
	}

	// mypage로 값보내기
	@RequestMapping(value = "/logincont/mypage.do")
	public ModelAndView mypagego(HttpSession hs) {
		ModelAndView mav = new ModelAndView("login/mypage");
		// 세션값으로 members값 가져오기
		int mem_no = (Integer) hs.getAttribute("no");
		System.out.println(mem_no);
		System.out.println("마이페이지로 가기");
		Member members = service.getmem_all(mem_no);
		System.out.println(members.getMem_id());
		mav.addObject("members", members);
		return mav;
	}

	@RequestMapping(value = "/logincont/dropoutpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView dropoutpage(@RequestParam(value = "mem_id") String mem_id) {
		ModelAndView mav = new ModelAndView("login/dropoutpage");
		String id = mem_id;
		mav.addObject("mem_id", id);
		return mav;
	}

	@RequestMapping(value = "/survey/main.do", method = RequestMethod.GET)
	public String startSurvey() {
		System.out.println("회원 성향 서베이 시작");
		return "survey/survey";
	}
	@RequestMapping(value = "/logincont/dropout.do")
	public ModelAndView dropout(Member m, HttpSession hs) {
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
		}else{
			System.out.println("비밀번호가 다르다.");
			mav.addObject("chk", chk);
		}
        return mav;
	}

	@RequestMapping(value = "/survey/survey.do", method = RequestMethod.POST)
	public ModelAndView surveypage(@RequestParam(value = "survey_Level") String survey_Level,
			@RequestParam(value = "survey_Answer") String survey_Answer) {
		ModelAndView mav = null;
		if (survey_Level.equals("1")) {
			mav = new ModelAndView("survey/survey");
			survey_Level = "1";
			mav.addObject("survey_Level", survey_Level);
			mav.addObject("survey_Answer", survey_Answer);
		} else if (survey_Level.equals("2")) {
			mav = new ModelAndView("survey/survey");
			survey_Level = "2";
			mav.addObject("survey_Level", survey_Level);
			mav.addObject("survey_Answer", survey_Answer);
		} else if (survey_Level.equals("3")) {
			mav = new ModelAndView("survey/survey");
			survey_Level = "3";
			mav.addObject("survey_Level", survey_Level);
			mav.addObject("survey_Answer", survey_Answer);
		} else if (survey_Level.equals("4")) {
			mav = new ModelAndView("survey/survey");
			survey_Level = "4";
			mav.addObject("survey_Level", survey_Level);
			mav.addObject("survey_Answer", survey_Answer);
		} else {
			mav = new ModelAndView("login/joinform");
			System.out.println(survey_Answer);
			mav.addObject("survey_Answer", survey_Answer);
		}
		return mav;

	}
}

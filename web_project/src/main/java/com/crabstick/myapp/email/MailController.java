package com.crabstick.myapp.email;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crabstick.myapp.login.LoginService;
import com.crabstick.myapp.login.Member;

@Controller
public class MailController {
	private int cert_num;
	@Resource(name = "loginService")
	private LoginService service;
	
	@Autowired 
	private JavaMailSender mailSender;
	
	private String from 	= "crabstick000@gmail.com";
	private String subject	= "안녕하세요 crabstick을 이용해주셔서 감사합니다. 비밀번호 변경 인증입니다.";

	
	@RequestMapping(value = "/emailCont/mail.do")
	public ModelAndView sendMail(Member m) {
		ModelAndView mav = new ModelAndView("login/findpassJSON");
		String id =  m.getMem_id();
		String pass = service.getmem_pass(m);
		
		if (pass == null) {
			pass = "0";
		}
		mav.addObject("pass", pass);
		System.out.println("비밀번호찾기완료");
		
		if(!pass.equals("0")){
			int load_num = certifyID.generateNum();
			cert_num = load_num;
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setTo(id);
				messageHelper.setText("인증번호 : " + load_num);
				messageHelper.setFrom(from);
				messageHelper.setSubject(subject);
				mailSender.send(message);
				System.out.println("전송완료 " + load_num);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return mav;
	}
	@RequestMapping(value = "/emailCont/chkPass.do")
	public String chkcert_num(Member m, @RequestParam(value="certify") int certify) {
		if(certify != cert_num){
			System.out.println("페스워드불일치");
			return "redirect:/logincont/findpassgo.do";
		}
		System.out.println("여기까지왔나?");
		return "login/changepass"; 
	}
}
package com.crabstick.myapp.email;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crabstick.myapp.login.LoginService;
import com.crabstick.myapp.login.Member;

@Controller
public class MailController {
	@Resource(name = "loginService")
	private LoginService service;
	
	@Autowired 
	private JavaMailSender mailSender;
	
	private String from 	= "crabstick000@gmail.com";
	private String subject	= "안녕하세요";
	
	@RequestMapping(value = "/emailCont/mail.do")
	public ModelAndView sendMail(Member m) {
		ModelAndView mav = new ModelAndView("login/findpassJSON");
		m.setMem_id("bongki1234@naver.com");
		m.setMem_name("123456");
		String pass = service.getmem_pass(m);
		if (pass == null) {
			pass = "0";
		}
		mav.addObject("pass", pass);
		System.out.println("비밀번호찾기완료");
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setTo(m.getMem_id());
			messageHelper.setText(pass);
			messageHelper.setFrom(from);
			messageHelper.setSubject(subject);
			mailSender.send(message);
		} catch(Exception e){
			System.out.println(e);
		}
		System.out.println("전송완료");
		return mav;
	}
	
}
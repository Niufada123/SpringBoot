package com.test.execption.controller;

import com.baomidou.kaptcha.Kaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author admin
 * @date
 */
@RestController
@RequestMapping("/kcaptcha")
public class KcaptchaController {

	@Autowired
	private Kaptcha kaptcha;



	@GetMapping("/generator")
	public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
		kaptcha.render();
	}

	@GetMapping("/verify")
	public String verify(String verifyCode, HttpServletRequest request) {

		Boolean aBoolean = kaptcha.validate(verifyCode, 100);
		if (aBoolean) {
			return "通过";
		}
		return "不通过";
	}

}

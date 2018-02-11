package com.ysf.controller;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ysf.bo.AuthResponse;
import com.ysf.common.enums.ExceptionEnum;
import com.ysf.common.excecption.BusinessException;
import com.ysf.service.IReqValidator;
import com.ysf.util.JwtTokenUtil;

@RestController
public class AuthController {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;	
	@Resource(name="simpleValidator")
	private IReqValidator reqValidator;
	
	@RequestMapping(value = "${jwt.auth-path}")
	public ResponseEntity<?> createAuthenticationToken(@RequestParam("userName")String userName,@RequestParam("password")String password) {
		boolean validate = reqValidator.validate(userName, password);
		if (validate) {
			final String randomKey = jwtTokenUtil.getRandomKey();
			final String token = jwtTokenUtil.generateToken(userName, randomKey);
			return ResponseEntity.ok(new AuthResponse(token,randomKey));
		}else {
			throw new BusinessException(ExceptionEnum.USER_OR_PASSWORD_ERROR.getCode().toString(),ExceptionEnum.USER_OR_PASSWORD_ERROR.getMessage());
		}
	}
}

package com.example.simpleBoard.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_Form";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup_Form";
		}
		
		if(!userCreateForm.getPassword().equals(userCreateForm.getPasswordConfirm())) {
			bindingResult.rejectValue("passwordConfirm", "passwordInCorrect", "비밀번호가 일치하지 않습니다");
			return "signup_form";
		}
		
		try {
			userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword());
		} catch(DataIntegrityViolationException err) {
			err.printStackTrace();
			bindingResult.reject("signupFailed", "[Email] 중복된 이메일");
			
			return "signup_form";
		} catch(Exception err) {
			err.printStackTrace();
			bindingResult.reject("signupFailed", err.getMessage());
			
			return "signup_form";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
}

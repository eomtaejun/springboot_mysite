package com.example.simpleBoard.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@Size(min=2, max=50)
	@NotEmpty(message="[Name] 필수 입력 항목")
	private String username;
	
	@NotEmpty(message="[password] 필수 입력 항목")
	private String password;
	
	@NotEmpty(message="[password confirm] 필수 입력 항목")
	private String passwordConfirm;
	
	@Email
	@NotEmpty(message="[email] 필수 입력 항목")
	private String email;
}

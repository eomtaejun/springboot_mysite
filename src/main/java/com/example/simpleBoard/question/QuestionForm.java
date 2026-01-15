package com.example.simpleBoard.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
	@NotEmpty(message="[subject] 필수 입력 항목")
	@Size(max=255)
	private String subject;

	@NotEmpty(message="[content] 필수 입력 항목")
	private String content;
}

package com.example.simpleBoard.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
	@NotEmpty(message="[comment] 필수 입력 항목")
	private String content;
}

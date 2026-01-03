package com.example.demo;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySiteApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void contextLoads() {
		Question q1=new Question();
		q1.setSubject("카이리 어빙 복귀 언제 하나요?");
		q1.setContent("ㅈㄱㄴ");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2=new Question();
		q2.setSubject("AD 뭐야");
		q2.setContent("어디로 트레이드 될까요");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}

}

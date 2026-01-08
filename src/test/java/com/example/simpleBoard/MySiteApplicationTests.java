package com.example.simpleBoard;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.simpleBoard.answer.Answer;
import com.example.simpleBoard.answer.AnswerRepository;
import com.example.simpleBoard.question.Question;
import com.example.simpleBoard.question.QuestionRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class MySiteApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Transactional /* method가 끝날 때까지 DB session이 종료되지 않게 함 */
	@Test
	void contextLoads() {
		/*
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
		*/
		
		/*
		List<Question> all=this.questionRepository.findAll();
		assertEquals(4, all.size());
		
		Question q=all.get(0);
		assertEquals("카이리 어빙 복귀 언제 하나요?", q.getSubject());
		*/
		
		/*
		Optional<Question> op=this.questionRepository.findById(4);
		if(op.isPresent()) {
			Question q=op.get();
			assertEquals("어디로 트레이드 될까요", q.getContent());
		}
		*/
		
		/*
		//Question q=this.questionRepository.findBySubject("카이리 어빙 복귀 언제 하나요?");
		Question q=this.questionRepository.findBySubjectAndContent("카이리 어빙 복귀 언제 하나요?", "ㅈㄱㄴ");
		assertEquals(1, q.getId());
		*/
		
		/*
		List<Question> qList=this.questionRepository.findBySubjectLike("%카이리%");
		Question q=qList.get(0);
		System.out.println(q.getSubject());
		*/
		
		/*
		Optional<Question> q=this.questionRepository.findById(2);
		assertTrue(q.isPresent());
		Question question=q.get();
		question.setSubject("AD 대신 트레이 영이 트레이트 됐네요?");
		this.questionRepository.save(question);
		*/
		
		/*
		Optional<Question> q=this.questionRepository.findById(1);
		assertTrue(q.isPresent());
		Question question=q.get();
		
		Answer a1=new Answer();
		a1.setContent("이번 시즌 안에 돌아올 겁니다");
		a1.setQuestion(question);
		a1.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a1);
		*/
		
		/*
		for(int i=1; i<=50; i++) {
			Question q1=new Question();
			q1.setSubject("테스트 코드를 이용해 생성한 제목: [제목"+i+"]");
			q1.setContent("테스트 코드를 이용해 생성한 내용: [내용"+i+"]");
			q1.setCreateDate(LocalDateTime.now());
			this.questionRepository.save(q1);
		}
		*/
		
		Optional<Question> q=this.questionRepository.findById(1);
		assertTrue(q.isPresent());
		Question question=q.get();
		
//		List<Answer> aList=this.answerRepository.findByQuestion(question);
		List<Answer> aList=question.getAnswerList();
		for(int i=0; i<aList.size(); i++) {
			Answer answer=aList.get(i);
			System.out.println(answer.getContent());
		}
		
		
		/*
		this.questionRepository.deleteById(3);
		this.questionRepository.deleteById(4);
		*/
	}

}

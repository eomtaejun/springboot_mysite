package com.example.simpleBoard.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.simpleBoard.DataNotFoundException;
import com.example.simpleBoard.answer.Answer;
import com.example.simpleBoard.user.SiteUser;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	
	public Page<Question> getList(int page, String keyword){
		List<Sort.Order> sorts=new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		Pageable pageable=PageRequest.of(page, 10, Sort.by(sorts));
		Specification<Question> spec=search(keyword);
		return this.questionRepository.findAll(spec, pageable);
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> question=this.questionRepository.findById(id);
		
		if(question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	// create(): 질문 저장하는 로직
	public void create(String subject, String content, SiteUser author) {
		Question question=new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		question.setAuthor(author);
		this.questionRepository.save(question);
	}
	
	public void update(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setUpdateDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}
	
	public void vote(Question question, SiteUser siteUser) {
		/* question.getVoter().add(siteUser); */
		if(question.getVoter().contains(siteUser)) {
			question.getVoter().remove(siteUser);
		} else {
			question.getVoter().add(siteUser);
		}
		
		this.questionRepository.save(question);
	}
	
	private Specification<Question> search(String keyword) {
		return new Specification<>() {
			private static final long serialVersionUID=1L;
			
			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<Question, SiteUser> qu=q.join("author", JoinType.LEFT);
				Join<Question, Answer> qa=q.join("answerList", JoinType.LEFT);
				Join<Answer, SiteUser> au=q.join("author", JoinType.LEFT);
				
				return cb.or(
					cb.like(q.get("subject"), "%"+keyword+"%"), 
					cb.like(q.get("content"), "%"+keyword+"%"), 
					cb.like(qu.get("username"), "%"+keyword+"%"), 
					cb.like(qa.get("content"), "%"+keyword+"%"), 
					cb.like(au.get("username"), "%"+keyword+"%")
				);
			}
		};
	}
}

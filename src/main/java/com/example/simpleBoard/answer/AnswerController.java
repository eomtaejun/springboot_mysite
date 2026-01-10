package com.example.simpleBoard.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.simpleBoard.question.Question;
import com.example.simpleBoard.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    private final AnswerRepository answerRepository;
	private final QuestionService questionService;

	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String Content) {
		Question question=this.questionService.getQuestion(id);
		
		this.answerService.create(question, Content);
		
		return "redirect:/question/detail/"+id;
	}
}

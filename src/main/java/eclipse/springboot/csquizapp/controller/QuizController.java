package eclipse.springboot.csquizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eclipse.springboot.csquizapp.model.Question;
import eclipse.springboot.csquizapp.model.QuestionWrapper;
import eclipse.springboot.csquizapp.model.Response;
import eclipse.springboot.csquizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) { 
		
		return quizService.createQuiz(category, numQ, title);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) { 
		
		return quizService.getQuizQuestions(id);
		
	}
	
//	purpose: testing
	@GetMapping("getallquizdetails/{id}")
	public ResponseEntity<List<Question>> getAllQuizDetails(@PathVariable Integer id) { 
		
		return quizService.getAllQuizDetails(id);
		
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) { 
		
		return quizService.calaculateResult(id, responses);
	}

}
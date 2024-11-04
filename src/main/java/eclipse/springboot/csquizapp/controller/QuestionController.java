package eclipse.springboot.csquizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eclipse.springboot.csquizapp.model.Question;
import eclipse.springboot.csquizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

	/*
	 * @GetMapping("allquestions") public List<Question> getAllQuestions() {
	 * 
	 * return questionService.getAllQuestions();
	 * 
	 * }
	 */
	
//	fetching all the questions
	@GetMapping("allquestions")
	public ResponseEntity<List<Question>> getAllQuestions() { 
		
	    ResponseEntity<List<Question>> question_response = questionService.getAllQuestions();
	    return question_response;
	    
	}

//	fetching a specific category question
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) { 
		
		ResponseEntity<List<Question>> question_response = questionService.getQuestionsByCategory(category);
		return question_response;
		
	}
	
//	adding a new question
	@PostMapping("addquestion")
	public  ResponseEntity<String> addQuestion(@RequestBody Question question) { 		
		
		ResponseEntity<String> response = questionService.addQuestion(question);
		return response;		
		
	}
	
//	delete a question
    @DeleteMapping("deletequestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        
		ResponseEntity<String> response = questionService.deleteQuestionById(id);
		return response;
		
    }
	
	
}

package eclipse.springboot.csquizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import eclipse.springboot.csquizapp.dao.QuestionDao;
import eclipse.springboot.csquizapp.dao.QuizDao;
import eclipse.springboot.csquizapp.model.Question;
import eclipse.springboot.csquizapp.model.Quiz;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) { 
		
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
		
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
	}
	
	
}

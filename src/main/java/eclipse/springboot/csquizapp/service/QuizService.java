package eclipse.springboot.csquizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import eclipse.springboot.csquizapp.dao.QuestionDao;
import eclipse.springboot.csquizapp.dao.QuizDao;
import eclipse.springboot.csquizapp.model.Question;
import eclipse.springboot.csquizapp.model.QuestionWrapper;
import eclipse.springboot.csquizapp.model.Quiz;
import eclipse.springboot.csquizapp.model.Response;
import jakarta.transaction.Transactional;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) { 
		
		try {
			
			List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
			
			
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestions(questions);
			
			quizDao.save(quiz);
			
			return new ResponseEntity<>("Quiz successfully created", HttpStatus.CREATED);
			
		} catch (Exception e) {
	        e.printStackTrace();
		}
		
		return new ResponseEntity<>("There's some issue in creating this quiz !", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Transactional
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) { 
	    try {
	        Optional<Quiz> quiz = quizDao.findById(id);
	        
	        // Check if the quiz is present
	        if (quiz.isPresent()) {
	            List<Question> questionsFromDB = quiz.get().getQuestions();
	            List<QuestionWrapper> questionsForUser = new ArrayList<>();

	            for (Question q : questionsFromDB) { 
	                QuestionWrapper qw = new QuestionWrapper(
	                    q.getId(), 
	                    q.getQuestionTitle(), 
	                    q.getOption1(), 
	                    q.getOption2(), 
	                    q.getOption3(), 
	                    q.getOption4()
	                );
	                questionsForUser.add(qw);
	            }
	            
	            return new ResponseEntity<>(questionsForUser, HttpStatus.OK); 
	        } else {
	            // Return an error response if the quiz is not found
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Transactional
	public ResponseEntity<Integer> calaculateResult(Integer id, List<Response> responses) { 
		
		try {
			
			Quiz quiz = quizDao.findById(id).get();
			List<Question> questions = quiz.getQuestions();
			int right = 0;
			int i = 0;
			
			for(Response response : responses) { 
				if(response.getResponse().equals(questions.get(i).getRightAnswer()))
				{
					right++; 
				}
				i++;
			}
			
			return new ResponseEntity<>(right, HttpStatus.OK);
			
		} catch (Exception e) {
			  e.printStackTrace();
		}
		
		return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

package eclipse.springboot.csquizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import eclipse.springboot.csquizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
	

}

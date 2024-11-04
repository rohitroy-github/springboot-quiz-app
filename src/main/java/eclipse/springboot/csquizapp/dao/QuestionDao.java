package eclipse.springboot.csquizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eclipse.springboot.csquizapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	
	public List<Question> findByCategory(String category);
	
}

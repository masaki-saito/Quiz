package pl.akademiakodu.quizDemo.repository;

import org.springframework.data.repository.CrudRepository;
import pl.akademiakodu.quizDemo.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    Question getQuestionById(Long id);
}

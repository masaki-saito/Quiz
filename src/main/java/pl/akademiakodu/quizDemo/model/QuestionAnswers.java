package pl.akademiakodu.quizDemo.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class QuestionAnswers {
    private List<String> questionAnswers = new ArrayList<>();

    public QuestionAnswers(){

    }

    public List<String> getQuestionAnswers() {
        return questionAnswers;
    }

    public void addQuestionAnswer(Integer index, String answer){
        questionAnswers.add(index, answer);
    }
}
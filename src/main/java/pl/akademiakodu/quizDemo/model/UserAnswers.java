package pl.akademiakodu.quizDemo.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserAnswers {
    private List<String> userAnswers = new ArrayList<>();

    public UserAnswers(){

    }

    public List<String> getUserAnswers() {
        return userAnswers;
    }

    public void addUserAnswer(Integer index, String userAnswer){
        userAnswers.add(index, userAnswer);
    }
}
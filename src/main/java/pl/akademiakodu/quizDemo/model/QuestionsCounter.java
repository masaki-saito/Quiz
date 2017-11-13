package pl.akademiakodu.quizDemo.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class QuestionsCounter {
    private Integer i = 0;

    public QuestionsCounter(){

    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i){
        this.i = i;
    }

    public void increment(){
        i++;
    }
}

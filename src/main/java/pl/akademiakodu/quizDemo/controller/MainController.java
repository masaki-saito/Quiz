package pl.akademiakodu.quizDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.akademiakodu.quizDemo.constants.ConstantQuestions;
import pl.akademiakodu.quizDemo.model.*;
import pl.akademiakodu.quizDemo.repository.QuestionRepository;

@Controller
public class MainController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private InitializeChecker initializeChecker;

    @Autowired
    private QuestionsCounter questionsCounter;

    @Autowired
    private QuestionAnswers questionAnswers;

    @Autowired
    private UserAnswers userAnswers;

    @GetMapping("/")
    public String index(){

        questionsCounter.setI(0);

        if(!initializeChecker.isInitialized()) {
            Question question0 = new Question();
            question0.setQuestion(ConstantQuestions.questions[0]);
            question0.setAnswer(ConstantQuestions.answers[0]);
            questionRepository.save(question0);

            Question question1 = new Question();
            question1.setQuestion(ConstantQuestions.questions[1]);
            question1.setAnswer(ConstantQuestions.answers[1]);
            questionRepository.save(question1);

            Question question2 = new Question();
            question2.setQuestion(ConstantQuestions.questions[2]);
            question2.setAnswer(ConstantQuestions.answers[2]);
            questionRepository.save(question2);

            initializeChecker.setInitialized(true);
        }

        return  "index";
    }

    @GetMapping("/question")
    public String question(@RequestParam String answer, ModelMap modelMap){
        userAnswers.addUserAnswer(questionsCounter.getI() - 1, answer);
        questionsCounter.increment();

        if(questionsCounter.getI() == ConstantQuestions.amountOfQuestions + 1){
            int amountOfCorrect = 0;
            int correctPercentage = 0;
            String message = null;


            for(int i = 0 ; i < ConstantQuestions.amountOfQuestions; i++){
                if(userAnswers.getUserAnswers().get(i).equals(questionAnswers.getQuestionAnswers().get(i))){
                    amountOfCorrect++;
                }
            }

            correctPercentage = (int)((double)amountOfCorrect / ConstantQuestions.amountOfQuestions * 100);

            if(correctPercentage == 100){
                message = "Congratulations!";
            } else if(correctPercentage >= 70 && correctPercentage < 100) {
                message = "Good Job";
            } else if(correctPercentage >= 50 && correctPercentage < 70) {
                message = "Not Bad";
            } else {
                message = "Try again";
            }

            modelMap.put("amountOfCorrect", amountOfCorrect);
            modelMap.put("correctPercentage", correctPercentage);
            modelMap.put("message", message);

            return "result";
        }

        Question q = questionRepository.getQuestionById(new Long(questionsCounter.getI()));
        questionAnswers.addQuestionAnswer(questionsCounter.getI() - 1, q.getAnswer());
        modelMap.put("question", q);
        return "questions";
    }

    @GetMapping("/initialize")
    public String initialize(ModelMap modelMap){
        questionsCounter.increment();
        Question q = questionRepository.getQuestionById(new Long(questionsCounter.getI()));
        questionAnswers.addQuestionAnswer(questionsCounter.getI() - 1, q.getAnswer());
        modelMap.put("question", q);
        return "questions";
    }
}

package Main.model;


import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Game implements Serializable {
    private int countStep=5;
    private int currentStep=0;
    private int trueAnswerCount=0;
    private String trueAnswer;

    public Game() {
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public int getCountStep() {
        return countStep;
    }

    public void setCountStep(int countStep) {
        this.countStep = countStep;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public int getTrueAnswerCount() {
        return trueAnswerCount;
    }

    public void setTrueAnswerCount(int trueAnswerCount) {
        this.trueAnswerCount = trueAnswerCount;
    }
}

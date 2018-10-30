package com.example.truefalsequiz;

import java.util.List;

public class Quiz {
    private List<Question> questions;
    private int score = 0;
    private int currentQuestion;

    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    public boolean checkAnswer(boolean answer) {
        if(questions.get(currentQuestion-1).isAnswer() == answer) {
            score ++;
            return true;
        } else {
            return false;
        }
    }

    public Question getMoreQuestions() {
        currentQuestion++;
        return questions.get(currentQuestion-1);
    }
    public boolean hasMoreQuestions() {
        return currentQuestion < questions.size();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}


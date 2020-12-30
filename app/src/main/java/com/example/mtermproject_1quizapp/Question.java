package com.example.mtermproject_1quizapp;

public class Question {
    private String category;
    private String questionnaire;
    private String[] optionAns;

    public Question(String category, String questionnaire, String[] optionAns) {
        this.category = category;
        this.questionnaire = questionnaire;
        this.optionAns = optionAns;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String[] getOptionAns() {
        return optionAns;
    }

    public void setOptionAns(String[] optionAns) {
        this.optionAns = optionAns;
    }
}

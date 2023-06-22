package com.example.licenta.utils;

import java.io.Serializable;

public class QuizResponse implements Serializable {

    private boolean likeToTravel;
    private boolean likeLuxury;
    private boolean isStudent;
    private boolean haveEconomies;
    private boolean wantBigLoan;
    private boolean multipleInvestments;

    public QuizResponse() {
    }

    public boolean isLikeToTravel() {
        return likeToTravel;
    }

    public void setLikeToTravel(boolean likeToTravel) {
        this.likeToTravel = likeToTravel;
    }

    public boolean isLikeLuxury() {
        return likeLuxury;
    }

    public void setLikeLuxury(boolean likeLuxury) {
        this.likeLuxury = likeLuxury;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public boolean isHaveEconomies() {
        return haveEconomies;
    }

    public void setHaveEconomies(boolean haveEconomies) {
        this.haveEconomies = haveEconomies;
    }

    public boolean isWantBigLoan() {
        return wantBigLoan;
    }

    public void setWantBigLoan(boolean wantBigLoan) {
        this.wantBigLoan = wantBigLoan;
    }

    public boolean isMultipleInvestments() {
        return multipleInvestments;
    }

    public void setMultipleInvestments(boolean multipleInvestments) {
        this.multipleInvestments = multipleInvestments;
    }
}

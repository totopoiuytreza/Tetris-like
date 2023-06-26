package com.example.tetrislike.usercontroller;

public class Score {
    private String rank = "";
    private String name = "";
    private String score = "";

    public Score(String rank, String name, String score) {
        this.rank = rank;
        this.name = name;
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

}

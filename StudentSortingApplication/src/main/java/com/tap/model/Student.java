package com.tap.model;

public class Student {
    private int id;
    private String name;
    private int englishMarks;
    private int mathsMarks;
    private int scienceMarks;
    private int socialMarks;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnglishMarks() {
        return englishMarks;
    }

    public void setEnglishMarks(int englishMarks) {
        this.englishMarks = englishMarks;
    }

    public int getMathsMarks() {
        return mathsMarks;
    }

    public void setMathsMarks(int mathsMarks) {
        this.mathsMarks = mathsMarks;
    }

    public int getScienceMarks() {
        return scienceMarks;
    }

    public void setScienceMarks(int scienceMarks) {
        this.scienceMarks = scienceMarks;
    }

    public int getSocialMarks() {
        return socialMarks;
    }

    public void setSocialMarks(int socialMarks) {
        this.socialMarks = socialMarks;
    }
}

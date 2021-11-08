package com.example.uasmcs_2301865842;

public class Definitions {

    int id;
    String imgURL;
    String type;
    String definition;
    String wordName1;

    public Definitions(int id, String imgURL, String type, String definition, String wordName1) {
        this.id = id;
        this.imgURL = imgURL;
        this.type = type;
        this.definition = definition;
        this.wordName1 = wordName1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getWordName1() {
        return wordName1;
    }

    public void setWordName1(String wordName1) {
        this.wordName1 = wordName1;
    }
}
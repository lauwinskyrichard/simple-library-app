package com.example.uasmcs_2301865842;

import java.util.ArrayList;

public class Word {

    int id;
    String wName;

    ArrayList<Definitions> definitions = new ArrayList<>();

    public Word(int id, String wName) {
        this.id = id;
        this.wName = wName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public ArrayList<Definitions> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<Definitions> definitions) {
        this.definitions = definitions;
    }
}

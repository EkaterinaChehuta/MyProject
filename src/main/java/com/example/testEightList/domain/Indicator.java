package com.example.testEightList.domain;

public class Indicator {
    private int id;
    private String name;
    private String viewName;

    public Indicator() {
    }

    public Indicator(int id, String name, String viewName) {
        this.id = id;
        this.name = name;
        this.viewName = viewName;
    }

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

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}

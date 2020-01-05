package com.shizuku.poetry.shijing;


public class Poet {
    private int value;
    private String title;
    private String chapter;
    private String section;
    private String content;

    public Poet(int value, String title, String chapter, String section, String content) {
        this.value = value;
        this.title = title;
        this.chapter = chapter;
        this.section = section;
        this.content = content;
    }

    public int getValue() {
        return value;
    }

    public String getChapter() {
        return chapter;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

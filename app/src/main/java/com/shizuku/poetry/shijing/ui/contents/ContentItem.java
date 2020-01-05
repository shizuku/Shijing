package com.shizuku.poetry.shijing.ui.contents;

public class ContentItem {
    private int level;
    private String item;
    public ContentItem(int l,String i){
        level = l;
        item = i;
    }
    public String getItem() {
        return item;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setItem(String item) {
        this.item = item;
    }

}

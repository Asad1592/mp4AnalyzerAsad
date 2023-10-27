package com.castlabs.task.mp4analyzer.model;

import java.util.List;

public class Box {
    private String type;
    private int size;
    private List<Box> subBoxes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Box> getSubBoxes() {
        return subBoxes;
    }

    public void setSubBoxes(List<Box> subBoxes) {
        this.subBoxes = subBoxes;
    }
}

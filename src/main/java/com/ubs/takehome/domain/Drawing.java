package com.ubs.takehome.domain;

public class Drawing {

    private Canvas canvas;

    private static final String NEW_LINE = "\n";

    public String output() {
        StringBuilder sb = new StringBuilder();
        for (char[] oneDimension : canvas.getGraph()) {
            for (char c : oneDimension) {
                sb.append(String.valueOf(c));
            }
            sb.append(NEW_LINE);
        }
        //Removing all newline char
        return sb.toString().replaceAll("\\n$","");
    }


    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}

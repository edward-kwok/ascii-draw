package com.ubs.takehome.domain;

public class Canvas {
    private final int width;
    private final int height;

    private final char[][] graph;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        graph = new char[height + 2][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getGraph() {
        return graph;
    }

}

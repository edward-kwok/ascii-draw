package com.ubs.takehome.domain;

public enum Color {
    VERTICAL('|'), HORIZONTAL('-'), LINE('x'), EMPTY(' ');

    private final char target;

    Color(char target) {
        this.target = target;
    }

    public char getColor() {
        return target;
    }
}

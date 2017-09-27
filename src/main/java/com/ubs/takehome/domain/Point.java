package com.ubs.takehome.domain;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Point {

    private final int xCoordinate;

    private final int yCoordinate;

    public Point(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xCoordinate == point.xCoordinate &&
                yCoordinate == point.yCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("xCoordinate", xCoordinate)
                .add("yCoordinate", yCoordinate)
                .toString();
    }
}

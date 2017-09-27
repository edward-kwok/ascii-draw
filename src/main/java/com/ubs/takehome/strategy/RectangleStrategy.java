package com.ubs.takehome.strategy;

import com.google.common.collect.ImmutableList;
import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.StrategyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class RectangleStrategy implements Strategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    private final Point upperLeft;
    private final Point lowerRight;

    public RectangleStrategy(Point upperLeft, Point lowerRight) {
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
    }


    @Override
    public void draw(Drawing drawing) throws StrategyException {
        Point lowerLeft = createLowerLeft(upperLeft, lowerRight);
        Point upperRight = createUpperRight(upperLeft, lowerRight);

        Strategy line1 = new LineStrategy(upperLeft, lowerLeft);
        Strategy line2 = new LineStrategy(lowerLeft, lowerRight);
        Strategy line3 = new LineStrategy(lowerRight, upperRight);
        Strategy line4 = new LineStrategy(upperRight, upperLeft);

        logger.debug("Creating Rectangle using Points:{},{},{},{}", line1.toString(), line2.toString(), line3.toString(), line4.toString());

        for (Strategy s : ImmutableList.of(line1, line2, line3, line4)) {
            s.draw(drawing);
        }
    }


    private Point createLowerLeft(Point upperLeft, Point lowerRight) {
        return new Point(upperLeft.getxCoordinate(), lowerRight.getyCoordinate());

    }


    private Point createUpperRight(Point upperLeft, Point lowerRight) {
        return new Point(lowerRight.getxCoordinate(), upperLeft.getyCoordinate());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RectangleStrategy that = (RectangleStrategy) o;
        return Objects.equals(upperLeft, that.upperLeft) &&
                Objects.equals(lowerRight, that.lowerRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upperLeft, lowerRight);
    }
}

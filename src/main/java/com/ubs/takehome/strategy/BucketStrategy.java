package com.ubs.takehome.strategy;

import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.BucketStrategyException;
import com.ubs.takehome.exception.DrawingStrategyException;
import com.ubs.takehome.exception.StrategyException;

import java.util.Objects;

public class BucketStrategy implements Strategy {

    private final Point point;

    private final char fill;


    public BucketStrategy(Point point, char fill) {
        this.fill = fill;
        this.point = point;

    }

    @Override
    public void draw(Drawing drawing) throws StrategyException {
         if (checkInsideCanvas(drawing, point)) {
            char currentPointColor = drawing.getCanvas().getGraph()[point.getyCoordinate()][point.getxCoordinate()];
            flood(point, fill, currentPointColor, drawing);
        } else {
            throw new BucketStrategyException("The filling point is not inside Canvas");
        }

    }


    private void flood(Point floodingPoint, char targetColor, char replacementColor, Drawing drawing) throws DrawingStrategyException {
        int x = floodingPoint.getxCoordinate();
        int y = floodingPoint.getyCoordinate();
        if (checkInsideCanvas(drawing, floodingPoint) && drawing.getCanvas().getGraph()[y][x] == targetColor) {
            return;
        }
        if (checkInsideCanvas(drawing, floodingPoint)) {

            if (drawing.getCanvas().getGraph()[y][x] == replacementColor) {
                drawing.getCanvas().getGraph()[y][x] = targetColor;
                flood(new Point(x - 1, y), targetColor, replacementColor, drawing);
                flood(new Point(x + 1, y), targetColor, replacementColor, drawing);
                flood(new Point(x, y - 1), targetColor, replacementColor, drawing);
                flood(new Point(x - 1, y + 1), targetColor, replacementColor, drawing);
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BucketStrategy that = (BucketStrategy) o;
        return Objects.equals(point, that.point) &&
                Objects.equals(fill, that.fill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, fill);
    }
}

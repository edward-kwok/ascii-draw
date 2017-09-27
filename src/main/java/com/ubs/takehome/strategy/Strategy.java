package com.ubs.takehome.strategy;

import com.ubs.takehome.domain.Canvas;
import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.DrawingStrategyException;
import com.ubs.takehome.exception.StrategyException;

public interface Strategy {

    /**
     * Draw on the Drawing domain, by the Strategy implementation
     *
     * @param drawing
     * @throws StrategyException
     */
    void draw(Drawing drawing) throws StrategyException;

    /**
     * Check if the provided Point is inside the Canvas from a Drawing
     *
     * @param drawing
     * @param point
     * @return
     * @throws DrawingStrategyException
     */
    default boolean checkInsideCanvas(Drawing drawing, Point point) throws DrawingStrategyException {
        if (drawing.getCanvas() == null){
            throw new DrawingStrategyException("Canvas not initialized");
        }
        Canvas canvas = drawing.getCanvas();
        return point.getxCoordinate() < canvas.getWidth() - 1  &&
                point.getxCoordinate() > 0
                && point.getyCoordinate() <= canvas.getHeight()
                && point.getyCoordinate() > 0;
    }

}

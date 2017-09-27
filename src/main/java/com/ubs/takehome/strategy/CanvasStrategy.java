package com.ubs.takehome.strategy;

import com.ubs.takehome.domain.Canvas;
import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.exception.CanvasStrategyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

import static com.ubs.takehome.domain.Color.*;

public class CanvasStrategy implements Strategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int width;

    private final int height;

    public CanvasStrategy(int width, int height) throws CanvasStrategyException {
        if(width > 2 && height > 2) {
            this.width = width;
            this.height = height;
        } else {
            throw new CanvasStrategyException("Width or Height should be larger than 2, otherwise nothing can be drawn inside!");
        }
    }


    @Override
    public void draw(Drawing drawing) {
        Canvas canvas = new Canvas(width, height);
        char[][] graph = canvas.getGraph();
        logger.debug("Initializing the canvas...");
        fill(graph);
        drawHeight(graph);
        drawWidth(graph);
        drawing.setCanvas(canvas);
        logger.debug("Canvas initialized and set into the drawing");
    }


    private void fill(char[][] graph) {
        logger.debug("Filling whole Canvas by {}", EMPTY.getColor());
        for (char[] oneDimension : graph) {
            Arrays.fill(oneDimension, EMPTY.getColor());
        }
    }

    private void drawHeight(char[][] graph) {
        logger.debug("Filling first and last column by '{}'", VERTICAL.getColor());
        for (int i = 0; i <= height; i++) {
            graph[i][0] = VERTICAL.getColor();
        }

        for (int i = 0; i <= height; i++) {
            graph[i][width - 1] = VERTICAL.getColor();
        }
    }


    private void drawWidth(char[][] graph) {
        logger.debug("Filling first and last row by '{}'", HORIZONTAL.getColor());
        Arrays.fill(graph[0], HORIZONTAL.getColor());
        Arrays.fill(graph[height + 1], HORIZONTAL.getColor());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CanvasStrategy that = (CanvasStrategy) o;
        return width == that.width &&
                height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}

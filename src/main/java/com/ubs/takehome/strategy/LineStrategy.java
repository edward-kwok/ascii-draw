package com.ubs.takehome.strategy;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Range;
import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.LineStrategyException;
import com.ubs.takehome.exception.PointException;
import com.ubs.takehome.exception.StrategyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static com.ubs.takehome.domain.Color.LINE;

@SuppressWarnings("unchecked")
public class LineStrategy implements Strategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    private final Point from;

    private final Point to;


    public LineStrategy(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void draw(Drawing drawing) throws StrategyException {
        if (checkInsideCanvas(drawing, from) && checkInsideCanvas(drawing, to) && drawing.getCanvas() != null) {
            Range xRange = from.getxCoordinate() < to.getxCoordinate() ? Range.closed(from.getxCoordinate(), to.getxCoordinate()) : Range.closed(to.getxCoordinate(), from.getxCoordinate());
            Range yRange = from.getyCoordinate() < to.getyCoordinate() ? Range.closed(from.getyCoordinate(), to.getyCoordinate()) : Range.closed(to.getyCoordinate(), from.getyCoordinate());

            ImmutableSortedSet<Integer> xSet = ContiguousSet.create(xRange, DiscreteDomain.integers());
            ImmutableSortedSet<Integer> ySet = ContiguousSet.create(yRange, DiscreteDomain.integers());
            logger.debug("Creating Line with x: {} y: {}", xSet, ySet);

            if (xSet.size() == 1 || ySet.size() == 1) {
                for (Integer x : xSet) {
                    for (Integer y : ySet) {
                        drawing.getCanvas().getGraph()[y][x] = LINE.getColor();
                    }
                }
                return;
            } else {
                throw new LineStrategyException("Points connected is not horizontal/vertical line");
            }
        }
        throw new PointException("Points not inside Canvas !");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineStrategy that = (LineStrategy) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("from", from)
                .add("to", to)
                .toString();
    }
}

package com.ubs.takehome.reader;

import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.strategy.RectangleStrategy;
import com.ubs.takehome.strategy.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RectangleReader implements Reader {

    private static final Pattern PATTERN = Pattern.compile("^(R|r)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$");

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Optional<Strategy> read(String input) throws StrategyException {
        Matcher m = PATTERN.matcher(input);
        if (!m.matches()) {
            return Optional.empty();
        }

        int x1 = Integer.parseInt(m.group(2));
        int x2 = Integer.parseInt(m.group(3));

        int y1 = Integer.parseInt(m.group(4));
        int y2 = Integer.parseInt(m.group(5));

        Point x = new Point(x1, x2);
        Point y = new Point(y1, y2);

        logger.debug("Creating RectangleStrategy using upperLeft: {} lowerRight: {}", x.toString(), y.toString());

        return Optional.of(new RectangleStrategy(x, y));
    }
}

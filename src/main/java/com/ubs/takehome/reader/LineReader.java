package com.ubs.takehome.reader;

import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.strategy.LineStrategy;
import com.ubs.takehome.strategy.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineReader implements Reader {

    private static final Pattern PATTERN = Pattern.compile("^(L|l)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$");

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Optional<Strategy> read(String input) throws StrategyException {
        Matcher m = PATTERN.matcher(input);
        if (!m.matches()) {
            logger.debug("Not matching LineStrategy: ({})", input);
            return Optional.empty();
        }

        int x1 = Integer.parseInt(m.group(2));
        int y1 = Integer.parseInt(m.group(3));

        int x2 = Integer.parseInt(m.group(4));
        int y2 = Integer.parseInt(m.group(5));
        Point y = new Point(x2, y2);
        Point x = new Point(x1, y1);
        logger.debug("Creating LineStrategy: x: ({}) y :({})", x, y);
        Strategy lineStrategy = new LineStrategy(x, y);

        return Optional.of(lineStrategy);
    }
}

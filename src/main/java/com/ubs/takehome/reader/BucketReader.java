package com.ubs.takehome.reader;

import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.strategy.BucketStrategy;
import com.ubs.takehome.strategy.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BucketReader implements Reader {

    private static final Pattern PATTERN = Pattern.compile("^(B|b)\\s+(\\d+)\\s+(\\d+)\\s(.{1})\\s*$");
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public Optional<Strategy> read(String input)  throws StrategyException {
        Matcher m = PATTERN.matcher(input);
        if (!m.matches()) {
            logger.debug("Not matching BucketStrategy: ({})", input);
            return Optional.empty();
        }

        int x1 = Integer.parseInt(m.group(2));
        int x2 = Integer.parseInt(m.group(3));
        char fill = m.group(4).charAt(0);

        Point point = new Point(x1, x2);
        logger.debug("Creating BucketStrategy: point:({}), fill:({})", point.toString(), fill);
        return Optional.of(new BucketStrategy(point, fill));
    }
}

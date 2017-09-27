package com.ubs.takehome.reader;

import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.strategy.CanvasStrategy;
import com.ubs.takehome.strategy.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CanvasReader implements Reader {

    private static final Pattern PATTERN = Pattern.compile("^(C|c)\\s+(\\d+)\\s+(\\d+)\\s*$");
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Optional<Strategy> read(String input) throws StrategyException{
        Matcher m = PATTERN.matcher(input);
        if (!m.matches()) {
            logger.debug("Not matching CanvasStrategy: ({})", input);
            return Optional.empty();
        }

        int width = Integer.parseInt(m.group(2));
        int height = Integer.parseInt(m.group(3));

        logger.debug("Creating CanvasStrategy width:({}), height:({})", width, height);

        return Optional.of(new CanvasStrategy(width, height));
    }
}

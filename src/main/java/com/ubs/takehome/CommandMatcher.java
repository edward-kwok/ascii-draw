package com.ubs.takehome;

import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.exception.StrategyMissingException;
import com.ubs.takehome.reader.*;
import com.ubs.takehome.strategy.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 *
 * This enum provides an execution place for matching the related strategies
 *
 */
public enum CommandMatcher {
    LINE(new LineReader()),
    RECT(new RectangleReader()),
    BUCKET(new BucketReader()),
    CANVAS(new CanvasReader()),
    QUIT(new QuitReader());


    private final Reader reader;
    private final static Logger logger = LoggerFactory.getLogger(App.class);


    public static void execute(String input, Drawing drawing) throws StrategyException {
        logger.info("Executing command: ({})", input);
        for (CommandMatcher m : CommandMatcher.values()) {
            Optional<Strategy> strategyOptional = m.getReader().read(input);
            if (strategyOptional.isPresent()) {
                strategyOptional.get().draw(drawing);
                return;
            }
        }
        throw new StrategyMissingException("Cannot find appropriate strategy for command: " + input);
    }


    CommandMatcher(Reader reader) {
        this.reader = reader;
    }

    private Reader getReader() {
        return reader;
    }
}

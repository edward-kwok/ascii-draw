package com.ubs.takehome.reader;

import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.strategy.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class QuitReader implements Reader {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Optional<Strategy> read(String input) throws StrategyException {
        isQuit(input);
        logger.debug("Quit not match: ({})", input);
        //Not exit, happily return empty Optional
        return Optional.empty();
    }

    private void isQuit(String line) {
        if (line.toLowerCase().startsWith("q")) {
            logger.debug("Quit matches: ({})", line);
            System.exit(0);
        }
    }
}

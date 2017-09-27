package com.ubs.takehome;

import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.exception.StrategyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class App {

    private static final String CONSOLE_ENTER_COMMAND = "enter command: ";
    private final Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {
        App app = new App();
        app.start(new Scanner(System.in));
    }

    public void start(Scanner scanner) {
        //Initialize a drawing
        logger.info("App starting...");
        Drawing drawing = new Drawing();
        while (true) {
            try {
                System.out.print(CONSOLE_ENTER_COMMAND);
                String line = scanner.nextLine();
                System.out.println("\n");
                CommandMatcher.execute(line, drawing);
                System.out.println(drawing.output());
                logger.info("Output: \n{}", drawing.output());
                System.out.print("\n");
            } catch (StrategyException e) {
                logger.error("Exception: ", e);
                System.out.println(e.getMessage());
            }
        }
    }
}

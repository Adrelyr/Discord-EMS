package fr.dreregon.discordems.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ConsoleHandler implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(ConsoleHandler.class);

    @Override
    public void run() {
        logger.info("Launching console thread");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String content = scanner.nextLine();
            //HANDLE STUFF
        }
    }
}

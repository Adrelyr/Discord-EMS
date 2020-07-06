package fr.dreregon.discordems.core.console;

import fr.dreregon.discordems.core.TokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleHandler implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(ConsoleHandler.class);

    private final Map<String, ConsoleCommand> commandMap = new HashMap<>();


    public void run() {
        commandMap.put("setToken", new TokenHandler());

        logger.info("Launching console thread");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String content = scanner.nextLine();
            handleCommand(content);
        }
    }

    private void handleCommand(String command){
        commandMap.get(command.split(" ")[0]).perform(command);
    }
}

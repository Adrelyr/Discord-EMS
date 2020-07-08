package fr.dreregon.discordems.core.console;

import fr.dreregon.discordems.core.Bot;
import fr.dreregon.discordems.core.ConsoleMode;
import fr.dreregon.discordems.core.Embed;
import fr.dreregon.discordems.core.TokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleHandler implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(ConsoleHandler.class);

    private final Map<String, ConsoleCommand> commandMap = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean waitForBuilderToFinish = true;

    private static ConsoleMode mode = ConsoleMode.COMMAND;


    public void run() {
        commandMap.put("setToken", new TokenHandler());
        commandMap.put("relaunch", new Bot());
        commandMap.put("embed", new Embed());

        logger.info("Launching console thread");

        while(scanner.hasNextLine()){
            String content = scanner.nextLine();
            switch (mode){
                case BUILDER:
                    redirectToBuilder();
                    break;
                case COMMAND:
                    handleCommand(content);
                    break;
            }
        }
    }

    private void redirectToBuilder() {
        try {
            while(waitForBuilderToFinish)
                this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void setMode(ConsoleMode newMode){
        mode = newMode;
        if(newMode == ConsoleMode.COMMAND) waitForBuilderToFinish = false;
    }

    private void handleCommand(String command){
        commandMap.get(command.split(" ")[0]).perform(command);
    }
}

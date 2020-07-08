package fr.dreregon.discordems.core;

import fr.dreregon.discordems.core.console.ConsoleHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Core {
    private final Logger logger = LoggerFactory.getLogger(Core.class);
    public static final String VERSION = "2.0.0";
    public static final File BASE_DIR = new File("DISCORD-EMS");
    public static final File CONFIG_DIR = new File(BASE_DIR.getAbsolutePath()+File.separatorChar+"CONFIG");
    public static final File SAVED_EMBEDS_DIR = new File(BASE_DIR.getAbsolutePath()+File.separatorChar+"SAVED_EMBEDS");
    public static SystemConfiguration configuration = new SystemConfiguration();

    public Core(){}

    public void launch(){
        logger.info("Launching Discord EMS "+VERSION);
        checkSystemFiles();
        launchConsoleThread();
        launchBot();
    }

    private void checkSystemFiles(){
        logger.info("Checking system folders...");
        if(!BASE_DIR.exists()){
            if(BASE_DIR.mkdir()) logger.info("Created Base directory.");
        }
        if(!CONFIG_DIR.exists()){
            if(CONFIG_DIR.mkdir()) logger.info("Created Config directory.");
        }
        if(!SAVED_EMBEDS_DIR.exists()){
            if(SAVED_EMBEDS_DIR.mkdir()) logger.info("Created Embed directory.");
        }

        if(!configuration.exists()){
            configuration.reset();
            logger.info("Created configuration file.");
        }
        configuration = configuration.get();
    }

    private void launchConsoleThread(){
        (new Thread(null,new ConsoleHandler(), "consoleHandler")).start();
    }

    private void launchBot(){
        if(configuration.getTokens().isEmpty()){
            logger.error("No tokens are saved. Please add a token using\n  setToken <token>\n Then start the bot by typing\n  relaunch");
        }
        Bot bot = new Bot(configuration.getTokens().get(0));
    }
}

package fr.dreregon.discordems.core;

import fr.dreregon.discordems.bot.Listener;
import fr.dreregon.discordems.core.console.ConsoleCommand;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot implements ConsoleCommand {
    @Getter @Setter
    private static JDA jda = null;

    public Bot(){}

    public Bot(String token){
        if(token.equals("")) return;
        if(jda != null) {
            if (jda.getStatus().equals(JDA.Status.CONNECTED)) {
                shutDown();
            }
        }
        try {
            jda = JDABuilder.createDefault(token).build().awaitReady();
            jda.addEventListener(new Listener());
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
    }

    public void shutDown(){
        jda.shutdownNow();
    }

    public void perform(String s) {
        new Bot(Core.configuration.getTokens().get(0));
    }
}

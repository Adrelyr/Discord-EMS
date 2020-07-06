package fr.dreregon.discordems.core;

import fr.dreregon.discordems.core.console.ConsoleCommand;

public class TokenHandler implements ConsoleCommand {

    public void storeToken(String token){
        SystemConfiguration systemConfiguration = new SystemConfiguration().get();
        systemConfiguration.getTokens().add(token);
        systemConfiguration.save();
    }

    @Override
    public void perform(String s) {
        String token = s.replace("setToken ","");
        storeToken(token);
    }
}

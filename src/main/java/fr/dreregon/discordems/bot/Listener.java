package fr.dreregon.discordems.bot;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends ListenerAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onReady(ReadyEvent event){
       logger.info("Bot"+event.getJDA().getSelfUser().getName()+event.getJDA().getSelfUser().getDiscriminator()+" connected");
    }
}

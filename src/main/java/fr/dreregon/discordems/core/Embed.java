package fr.dreregon.discordems.core;

import fr.dreregon.discordems.core.console.ConsoleCommand;
import fr.dreregon.discordems.core.console.ConsoleHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Embed implements ConsoleCommand {
    private static MessageEmbed messageEmbed = null;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void perform(String s) {
        String[] args = s.split(" ");
        if(args.length == 2 && args[1].equals("test")){
            messageEmbed = buildTestEmbed();
            return;
        }
        messageEmbed = buildEmbed();
        sendEmbed();
    }

    public MessageEmbed buildEmbed(){
        ConsoleHandler.setMode(ConsoleMode.BUILDER);
        System.out.println("Title:");
        String title = scanner.nextLine();
        System.out.println("Description:");
        String description = scanner.nextLine();
        System.out.println("Color (r,g,b):");
        String[] colors = scanner.nextLine().split(",");
        Color color = new Color(Integer.parseInt(colors[0]),Integer.parseInt(colors[1]),Integer.parseInt(colors[2]));

        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(color).build();
    }

    public MessageEmbed buildTestEmbed(){
        return new EmbedBuilder()
                .setTitle("Title")
                .setDescription("Description")
                .setAuthor("Author")
                .setFooter("Footer")
                .setColor(Color.ORANGE)
                .addBlankField(true)
                .addBlankField(true)
                .addField("Field 1", "Description 1",false).build();
    }

    public void sendEmbed(){
        System.out.println("Send in guild:");
        int count=0;
        List<Guild> guilds = new ArrayList<>();
        for(Guild guild : Bot.getJda().getGuilds()){
            System.out.println(count+"-"+guild.getName());
            guilds.add(guild);
            count++;
        }
        System.out.print("Pick a number : ");
        Guild destination = guilds.get(scanner.nextInt());
        System.out.println("In channel :");
        int count2=0;
        List<TextChannel> channels = new ArrayList<>();
        for(TextChannel channel : destination.getTextChannels()){
            System.out.println(count2+"-"+channel.getName());
            channels.add(channel);
            count2++;
        }
        System.out.print("Pick a number : ");
        TextChannel destinationChannel = channels.get(scanner.nextInt());

        destinationChannel.sendMessage(messageEmbed).queue();
        ConsoleHandler.setMode(ConsoleMode.COMMAND);
    }
}

package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Embed implements ICommand {

    @Override
    public String getName() {
        return "embed";
    }

    @Override
    public String getDescription() {
        return "임베드를 출력합니다";
    }
    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        /*data.add(new OptionData(OptionType.STRING, "title", "title", true));
        data.add(new OptionData(OptionType.STRING, "Description", "Description", true));
        data.add(new OptionData(OptionType.INTEGER, "fieldnum", "fieldnum", true));
        data.add(new OptionData(OptionType.STRING, "field", "field", true));
        data.add(new OptionData(OptionType.STRING, "setColor", "setColor", true));
        data.add(new OptionData(OptionType.STRING, "setFooter", "setFooter", true));*/
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        EmbedBuilder builder = new EmbedBuilder();
        /*builder.setTitle("asd");
        builder.setDescription("asd");
        builder.addField("asd","a",true);
        builder.setFooter("asd");
        builder.setColor(Color.BLUE);
        builder.appendDescription("this has been added");
        builder.setAuthor("asd");*/
        event.replyEmbeds(builder.build()).queue();

    }
}

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
        data.add(new OptionData(OptionType.STRING, "title", "", false)
                .setMinValue(1)
                .setMaxValue(10000));
        data.add(new OptionData(OptionType.STRING, "Description", "", false)
                .setMinValue(1)
                .setMaxValue(10000));
        data.add(new OptionData(OptionType.INTEGER, "field num", "", false)
                .setMinValue(1)
                .setMaxValue(10000));
        data.add(new OptionData(OptionType.STRING, "field", "", false)
                .setMinValue(1)
                .setMaxValue(10000));

        data.add(new OptionData(OptionType.STRING, "setColor", "", false)
                .setMinValue(1)
                .setMaxValue(10000));

        data.add(new OptionData(OptionType.STRING, "setFooter", "", false)
                .setMinValue(1)
                .setMaxValue(10000));


        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("asd");
        builder.setDescription("asd");
        builder.addField("asd","a",true);
        builder.addField("asd","a",true);
        builder.addField("asd","a",true);
        builder.setFooter("asd");
        builder.setColor(Color.BLUE);
        builder.appendDescription("this has been added");
        builder.setAuthor("asd");
        event.replyEmbeds(builder.build()).queue();

    }
}

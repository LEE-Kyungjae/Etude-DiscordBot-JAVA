package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Sumv2 implements ICommand {


    @Override
    public String getName() {
        return "sum2";
    }

    @Override
    public String getDescription() {
        return "sumv2 Will take the sum of two numbers";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.INTEGER, "number1", "The first number", true)
                .setMinValue(1)
                .setMaxValue(2));
        data.add(new OptionData(OptionType.INTEGER, "number2", "The second number", true)
                .setMinValue(1)
                .setMaxValue(2));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping number1 = event.getOption("number1");
        int num1 = number1.getAsInt();
        OptionMapping number2 = event.getOption("number2");
        int num2 = 1;
        if (number2 != null) {
            num2 = number2.getAsInt();
        }
        int result = num1 + num2;
        event.reply(Integer.toString(result)).queue();
    }
}

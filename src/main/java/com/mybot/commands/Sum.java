package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Sum implements ICommand {


    @Override
    public String getName() {
        return "sum";
    }

    @Override
    public String getDescription() {
        return "두수의 덧셈연산을 수행합니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.INTEGER, "number1", "첫번째 숫자", true)
                .setMinValue(1)
                .setMaxValue(10000));
        data.add(new OptionData(OptionType.INTEGER, "number2", "두번째 숫자", true)
                .setMinValue(1)
                .setMaxValue(10000));
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

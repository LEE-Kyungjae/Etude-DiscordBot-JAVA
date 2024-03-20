package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld implements ICommand {
    @Override
    public String getName() {
        return "helloworld";
    }

    @Override
    public String getDescription() {
        return "Hello World 라고 답장합니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Hello World!").queue();
    }
}

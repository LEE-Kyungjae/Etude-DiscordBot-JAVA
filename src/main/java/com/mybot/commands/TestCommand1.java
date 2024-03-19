package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class TestCommand1 implements ICommand {
    @Override
    public String getName() {
        return "cmd1";
    }

    @Override
    public String getDescription() {
        return "테스트 커멘드 1입니다";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

    }
}

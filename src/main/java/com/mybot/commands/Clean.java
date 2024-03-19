package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Clean implements ICommand {
    @Override
    public String getName() {
        return "clean";
    }

    @Override
    public String getDescription() {
        return "채팅 삭제 커멘드입니다. 삭제할 줄수를 입력하세요";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.INTEGER, "line", "how many delete line?", true)
                .setMinValue(1)
                .setMaxValue(100));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping command = event.getOption("line");
        int line = command.getAsInt();

        int resultInt = 0;
        String resultString = "에러발생";
        if (resultInt > 0) {
            resultString = "삭제가 완료되었습니다";
        }
        event.reply(resultString).queue();
    }
}

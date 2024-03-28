package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;


public class Cancel implements ICommand {

    private final KickAllVoiceMember kickAllVoiceMember;

    public Cancel(KickAllVoiceMember kickAllVoiceMember) {
        this.kickAllVoiceMember = kickAllVoiceMember;
    }

    @Override
    public String getName() {
        return "cancel";
    }

    @Override
    public String getDescription() {
        return "스케줄링한 커멘드를 취소합니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (kickAllVoiceMember.getScheduledTask() != null && !kickAllVoiceMember.getScheduledTask().isDone()) {
            kickAllVoiceMember.getScheduledTask().cancel(true);
            event.reply("스케줄된 작업이 취소되었습니다.").queue();
        } else {
            event.reply("스케줄된 작업이 없거나 이미 완료되었습니다.").queue();
        }
    }
}

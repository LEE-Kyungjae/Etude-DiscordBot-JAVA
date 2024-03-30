package com.mybot.commands;

import com.mybot.GuildMusicManager;
import com.mybot.ICommand;
import com.mybot.PlayerManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collections;
import java.util.List;

public class StopCommand implements ICommand {

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "Stops the current track and clears the queue.";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList(); // 이 커맨드는 추가적인 옵션이 필요하지 않습니다.
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        GuildMusicManager musicManager = PlayerManager.getInstance().getGuildMusicManager(event.getGuild());

        musicManager.audioPlayer.stopTrack(); // 현재 트랙을 정지합니다.
        musicManager.scheduler.clearQueue(); // TrackScheduler 내의 메서드를 통해 대기열을 비웁니다.

        event.reply("음악 재생을 정지하고 대기열을 비웠습니다.").queue();
    }
}

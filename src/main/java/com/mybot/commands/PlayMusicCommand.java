package com.mybot.commands;

import com.mybot.GuildMusicManager;
import com.mybot.ICommand;
import com.mybot.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Collections;
import java.util.List;

public class PlayMusicCommand implements ICommand {

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "Plays music from a YouTube URL";
    }

    @Override
    public List<OptionData> getOptions() {
        OptionData urlOption = new OptionData(OptionType.STRING, "url", "The YouTube URL to play", true);
        return Collections.singletonList(urlOption);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String url = event.getOption("url").getAsString();
        AudioPlayerManager playerManager = PlayerManager.getInstance().getAudioPlayerManager();

        playerManager.loadItemOrdered(event.getGuild(), url, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {

                GuildMusicManager musicManager = PlayerManager.getInstance().getGuildMusicManager(event.getGuild());
                musicManager.scheduler.queue(audioTrack);

                AudioManager audioManager = event.getGuild().getAudioManager();
                if (!audioManager.isConnected() && !audioManager.isAutoReconnect()) {
                    VoiceChannel memberChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
                    if (memberChannel != null) {
                        audioManager.openAudioConnection(memberChannel);
                        audioManager.setSendingHandler(musicManager.getSendHandler());
                    }
                }
                event.reply("노래 재생을 시작합니다: " + audioTrack.getInfo().title).queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                // 플레이리스트 로딩 로직, 선택적으로 구현
            }

            @Override
            public void noMatches() {
                // URL에서 트랙을 찾을 수 없을 때의 처리
                event.reply("노래를 찾을 수 없습니다.").queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                // 로딩 실패 처리
                event.reply("노래 로딩에 실패했습니다.").queue();
            }
        });
    }
}
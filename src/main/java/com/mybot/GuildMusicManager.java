package com.mybot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GuildMusicManager extends AudioEventAdapter {
    public final AudioPlayer audioPlayer;
    public final TrackScheduler scheduler;

    public GuildMusicManager(AudioPlayerManager manager) {
        this.audioPlayer = manager.createPlayer();
        this.scheduler = new TrackScheduler(this.audioPlayer);
        this.audioPlayer.addListener(this.scheduler);
    }

    public AudioSendHandler getSendHandler() {
        return new AudioPlayerSendHandler(audioPlayer);
    }

    public static class TrackScheduler extends AudioEventAdapter {
        private final AudioPlayer player;
        private final BlockingQueue<AudioTrack> queue;

        public TrackScheduler(AudioPlayer player) {
            this.player = player;
            this.queue = new LinkedBlockingQueue<>();
        }

        public void queue(AudioTrack track) {
            if (!this.player.startTrack(track, false)) {
                queue.offer(track);
            }
        }

        @Override
        public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
            if (endReason.mayStartNext) {
                nextTrack();
            }
        }

        public void nextTrack() {
            // 대기열에서 다음 트랙을 꺼내어 재생하거나, 대기열이 비어있으면 재생하지 않음
            AudioTrack nextTrack = queue.poll();
            if (!player.startTrack(nextTrack, false)) {
                // 다음 트랙이 없으면 플레이어가 아무것도 재생하지 않도록 설정
            }
        }

        public void clearQueue() {
            queue.clear();
        }
    }
}

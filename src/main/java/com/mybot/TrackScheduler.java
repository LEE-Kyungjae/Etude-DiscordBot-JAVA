package com.mybot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void queue(AudioTrack track) {
        // 현재 재생 중인 트랙이 없다면 바로 재생, 그렇지 않다면 대기열에 추가
        if (!this.player.startTrack(track, true)) {
            queue.offer(track);
        }
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        // 트랙이 자연적으로 종료되었거나, 'REPLACED' 경우 다음 트랙 재생
        if (endReason.mayStartNext) {
            nextTrack();
        }
    }

    /**
     * 대기열의 다음 트랙을 재생합니다. 대기열이 비어있다면 재생하지 않습니다.
     */
    public void nextTrack() {
        AudioTrack nextTrack = queue.poll();
        // 대기열에서 다음 트랙을 가져와 재생. 없다면 재생하지 않음 (null 전달)
        player.startTrack(nextTrack, false);
    }

    /**
     * 현재 재생 중인 트랙을 중지하고, 대기열의 첫 번째 트랙을 재생합니다.
     */
    public void skipTrack() {
        nextTrack();
    }

    /**
     * 대기열을 비웁니다.
     */
    public void clearQueue() {
        queue.clear();
    }
}

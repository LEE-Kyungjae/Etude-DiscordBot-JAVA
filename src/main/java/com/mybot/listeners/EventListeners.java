package com.mybot.listeners;

import net.dv8tion.jda.api.entities.User;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListeners extends ListenerAdapter {

    //채팅리엑션 이모지 리스너
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        //TechnoVision reacted to a message with "따봉" in th Test
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumpLink = event.getJumpUrl();
        assert user != null;
        String message = user.getAsTag() + "reacted to a message with " +emoji +  "in the " + channelMention + "channel!";
        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(message).queue();

    }
    //채널에 메시지 수신리스너
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        //봇채팅 무시
        if (event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        event.getChannel().sendMessage("pong").queue();

    }
    //서버에 사람들어왔을때
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        User user = event.getUser();
        // 이벤트가 발생한 서버의 텍스트채널을 모두 받아와서 그중에 아무거나 존재한다면 텍스트채널에 메시지를 전송
//        event.getGuild().getTextChannels()
//                .stream().findAny().ifPresent(
//                        textChannel -> textChannel.sendMessage("hi "+user).queue()
//                );
// 대표 텍스트 채널에 메시지를 전송
        String message = "hi "+user;
        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(message).queue();
    }

    //온라인인 유저의 메시지 열람
    @Override
    public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {
        User user = event.getUser();
        String message = " ** " + user.getAsMention() + "** updated their online status!";
        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(message).queue();
    }
}

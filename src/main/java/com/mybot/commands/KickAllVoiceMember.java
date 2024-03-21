package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class KickAllVoiceMember implements ICommand {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Override
    public String getName() {
        return "voutall";
    }

    @Override
    public String getDescription() {
        return "지정한 시간 후에 해당 보이스채널에 입장하고 있는 모든 유저를 내보냅니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.INTEGER, "minute", "분", true)
                .setMinValue(1)
                .setMaxValue(10000));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        int minute = Integer.parseInt(event.getOption("minute").getAsString());
        if (minute <= 0) {
            event.reply("시간은 양의 정수여야 합니다.").queue();
            return;
        }
        Member member = event.getMember();
        if (member == null) {
            event.reply("오류: 사용자 정보를 찾을 수 없습니다.").setEphemeral(true).queue();
            return;
        }
        VoiceChannel voiceChannel = (VoiceChannel) member.getVoiceState().getChannel();
        if (voiceChannel == null) {
            event.reply("죄수가 보이스 채널에 접속 중이 아닙니다.").queue();
            return;
        }
        List<Member> members = voiceChannel.getMembers();

        event.deferReply().setContent("가 죄수 " + memberString(members) + "를 찾는중이에요\n(" + minute + "분후에 찾을예정)").queue(); // 응답 지연
        scheduler.schedule(() -> {
            for (Member voice_member : members) {
                member.getGuild().kickVoiceMember(member).queue(
                        success -> event.getHook().sendMessage("찾았다 요놈들 \"또각\"\n(보이스채널에있는 모든사용자를 내보냈습니다.)").queue(), // 응답 지연 해제
                        failure -> event.getHook().sendMessage("오류가 발생했습니다 아마 보이스채널에 없으신거같아요" + failure.getMessage()).queue() // 응답 지연 해제
                );
            }
        }, minute, TimeUnit.SECONDS);
    }

    private String memberString(List<Member> members) {
        boolean cnt = false;
        String result = "";
        for (Member member : members) {
            String nickname = member.getNickname();
            if (nickname == null) {
                nickname = member.getUser().getName(); // 기본 유저명 사용
            }
            if (cnt = false) {
                result += nickname;
            } else {
                result += " " + nickname;
            }
            cnt = true;
        }
        result.replace(" ", ", ");
        return result;
    }
}

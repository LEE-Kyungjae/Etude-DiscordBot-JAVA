package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto implements ICommand {
    @Override
    public String getName() {
        return "lotto";
    }

    @Override
    public String getDescription() {

        return "로또번호를 추첨합니다!";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String lottoNumbers = generateLottoNumbers();
        event.reply("오늘의 추천 로또번호는 " + lottoNumbers + " 입니다");

    }

    public static String generateLottoNumbers() {
        List<Integer> numbers = new ArrayList<>();

        // 1부터 45까지의 숫자를 리스트에 추가
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }

        // 리스트를 무작위로 섞음
        Collections.shuffle(numbers);

        // 앞에서부터 6개의 숫자를 선택하여 문자열로 반환
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(numbers.get(i));
            if (i != 5) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}

package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sudoku implements ICommand {

    @Override
    public String getName() {
        return "sudoku";
    }

    @Override
    public String getDescription() {
        return "스도쿠 게임을 시작합니다!";
    }

    @Override
    public List<OptionData> getOptions() {
        return new ArrayList<>();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        int[][] puzzle = generatePuzzle();
        String formattedPuzzle = formatPuzzle(puzzle);
        event.reply(formattedPuzzle).queue();
    }

    private int[][] generatePuzzle() {
        // 여기에 스도쿠 퍼즐 생성 로직을 구현합니다.
        // 이 예시에서는 무작위로 생성된 스도쿠 퍼즐을 반환합니다.
        int[][] puzzle = new int[9][9];
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = random.nextInt(9) + 1;
            }
        }
        return puzzle;
    }

    private String formatPuzzle(int[][] puzzle) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(puzzle[i][j]);
                if (j != 8) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
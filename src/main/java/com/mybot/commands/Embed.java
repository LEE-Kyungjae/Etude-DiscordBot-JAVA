package com.mybot.commands;

import com.mybot.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Embed implements ICommand {

    @Override
    public String getName() {
        return "embed";
    }

    @Override
    public String getDescription() {
        return "임베드를 출력합니다";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.STRING, "title", "Title", false));
        data.add(new OptionData(OptionType.STRING, "description", "Description", false));
        data.add(new OptionData(OptionType.STRING, "field", "Field", false));
        data.add(new OptionData(OptionType.STRING, "color", "Color (Hex or RGB)", false));
        data.add(new OptionData(OptionType.STRING, "footer", "Footer", false));
        data.add(new OptionData(OptionType.STRING, "thumbnail", "Thumbnail URL", false));
        data.add(new OptionData(OptionType.STRING, "image", "Image URL", false));
        data.add(new OptionData(OptionType.STRING, "author", "Author Name", false));
        data.add(new OptionData(OptionType.STRING, "timestamp", "Include Timestamp", false));
        data.add(new OptionData(OptionType.STRING, "url", "URL", false));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        // Title
        String title = event.getOption("title") != null ? event.getOption("title").getAsString() : null;
        if (title != null) {
            builder.setTitle(title);
        }

        // Description
        String description = event.getOption("description") != null ? event.getOption("description").getAsString() : null;
        if (description != null) {
            builder.setDescription(description);
        }
        // Field
        String field = event.getOption("field") != null ? event.getOption("field").getAsString() : null;
        if (field != null) {
            builder.addField("Field", field, false);
        }
        // Color
        String color = event.getOption("color") != null ? event.getOption("color").getAsString() : null;
        if (color != null) {
            builder.setColor(colorInverter(color));
        }
        // Footer
        String footer = event.getOption("footer") != null ? event.getOption("footer").getAsString() : null;
        if (footer != null) {
            builder.setFooter(footer);
        }
        String thumbnail = event.getOption("thumbnail") != null ? event.getOption("thumbnail").getAsString() : null;
        if (thumbnail != null) {
            builder.setThumbnail("https://example.com/thumbnail.png");
        }

        // Image 추가
        String image = event.getOption("image") != null ? event.getOption("image").getAsString() : null;
        if (thumbnail != null) {
            builder.setImage(image);
        }

        // Author 추가
        String author = event.getOption("author") != null ? event.getOption("author").getAsString() : null;
        if (author != null) {
            builder.setAuthor(author, "https://example.com/author", "https://example.com/avatar.png");
        }

        // Timestamp 추가 (현재 시간 사용)
        String timeStamp = event.getOption("timeStamp") != null ? event.getOption("timestamp").getAsString() : null;
        if (author != null) {
            builder.setTimestamp(Instant.now());
        }
        String url = event.getOption("url") != null ? event.getOption("url").getAsString() : null;
        if (url != null) {
            builder.setUrl(url);
        }

        event.replyEmbeds(builder.build()).queue();
    }
    //사용자가 헥사코드를 입력했을경우
    public static Color hexToColor(String hexCode) {
        return new Color(
                Integer.valueOf(hexCode.substring(1, 3), 16),
                Integer.valueOf(hexCode.substring(3, 5), 16),
                Integer.valueOf(hexCode.substring(5, 7), 16)
        );
    }
    // 사용자가 RGB값을 입력했을경우
    public static Color rgbToColor(String userInput) {
        String[] rgbValues = userInput.split(",");
        int r = Integer.parseInt(rgbValues[0].trim());
        int g = Integer.parseInt(rgbValues[1].trim());
        int b = Integer.parseInt(rgbValues[2].trim());
        return new Color(r, g, b);
    }
    // 사용자가 색상을 입력받았을때 올바른 형식으로 인버터
    public static Color colorInverter(String userInput){
        if (userInput.contains(",")) {
            return rgbToColor(userInput);
        } else if (userInput.startsWith("#") && userInput.length() == 7) {
            return hexToColor(userInput);
        } else {
            System.out.println("잘못된 입력입니다.");
        }
        return Color.CYAN;
    }
}
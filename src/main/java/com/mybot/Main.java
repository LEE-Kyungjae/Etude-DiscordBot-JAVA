package com.mybot;

import com.mybot.commands.Sum;
import com.mybot.commands.Sumv2;
import com.mybot.commands.Clean;
import com.mybot.listeners.EventListeners;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;


public class Main {
    private final Dotenv config;
    private final ShardManager shardManager;

    public Main() {
        //토큰 가져오기
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        //앱 상태 지정
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder
                .createDefault(token)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("죄수랑 술래잡기"))
                .enableIntents(GatewayIntent.GUILD_MEMBERS,GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_PRESENCES);

        shardManager = builder.build();

        //기본 매니저를 통한 지정
        shardManager.addEventListener(new EventListeners());
        shardManager.addEventListener(new Sum());

        //직접 커스텀한 커멘드 매니저를 통한 커멘드등록
        CommandManager manager = new CommandManager();
        manager.add(new Sumv2());
        manager.add(new Clean());
        shardManager.addEventListener(manager);
    }
    public  ShardManager getShardManager(){
        return shardManager;
    }
    public Dotenv getConfig(){
        return config;
    }

    public static void main(String[] args) throws LoginException {
        Main m = new Main();
    }
}
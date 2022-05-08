package discord.bots.annoy.bot;

import java.util.EnumSet;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.stereotype.Component;

@Component
public class DonnieBot {

  public DonnieBot () throws LoginException {
    //TODO: hide token
    JDABuilder.createDefault(
        "OTcyNTczOTIyODQ5NTk1NDYy.GqrHeu.9XVO5skXme3gnmgWSoBD2LCkZ9owibvfcjLMr4",
        GatewayIntent.GUILD_MEMBERS,
        GatewayIntent.GUILD_MESSAGES,
        GatewayIntent.GUILD_VOICE_STATES
    ).disableCache(EnumSet.of(
            CacheFlag.CLIENT_STATUS,
            CacheFlag.ACTIVITY,
            CacheFlag.EMOTE
        ))
        .enableCache(CacheFlag.VOICE_STATE)
        .addEventListeners(new EventListener())
        .setActivity(Activity.watching("you while you sleep"))
        .build();
  }
}

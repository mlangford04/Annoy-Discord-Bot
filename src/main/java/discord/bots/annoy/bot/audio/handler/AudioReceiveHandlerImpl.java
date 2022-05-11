package discord.bots.annoy.bot.audio.handler;

import javax.annotation.Nonnull;
import discord.bots.annoy.bot.audio.GuildAudioManager;
import discord.bots.annoy.bot.audio.PlayerManager;
import discord.bots.annoy.bot.command.CommandContext;
import net.dv8tion.jda.api.audio.AudioNatives;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

public class AudioReceiveHandlerImpl implements AudioReceiveHandler {

  private GuildAudioManager guildAudioManager;
  private final List<String> usersToAnnoy;
  private final User callingUser;
  private static final String DONNIE_1_HOUR_AUDIO_LINK = "https://www.youtube.com/watch?v=0XqDzxPY40A";


  public AudioReceiveHandlerImpl(CommandContext ctx) {
    this.guildAudioManager = PlayerManager.getInstance().getAudioManager(ctx.getGuild());
    this.usersToAnnoy = ctx.getArgs();
    this.callingUser = ctx.getMember().getUser();

    PlayerManager.getInstance().loadAndPlay(ctx.getTextChannel(), DONNIE_1_HOUR_AUDIO_LINK);
    AudioNatives.ensureOpus();
  }

  @Override
  public boolean canReceiveCombined() { return true; }

  public void handleCombinedAudio(@Nonnull CombinedAudio combinedAudio) {
    guildAudioManager.getScheduler().getPlayer().setPaused(shouldPause(combinedAudio.getUsers()));
  }

  private boolean shouldPause(List<User> usersSpeaking) {
    //TODO: Don't annoy the person who called the command
    //Don't annoy the person who made the command
//    List<User> usersSpeaking  = new ArrayList<>();
//    Collections.copy(usersSpeaking, combinedUsers);
//    usersSpeaking.remove(callingUser);

    //Always pause when no one is speaking (or only the person who made the command)
    if(usersSpeaking == null || usersSpeaking.isEmpty()) return true;

    //No args given --> annoy everyone
    if(usersToAnnoy == null || usersToAnnoy.isEmpty())  return false;

    //Args given --> Only annoy users given
    return !usersSpeaking.stream().anyMatch(userSpeaking -> usersToAnnoy.contains(userSpeaking.getName()));
  }


}

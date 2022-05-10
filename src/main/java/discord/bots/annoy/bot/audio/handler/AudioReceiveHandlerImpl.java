package discord.bots.annoy.bot.audio.handler;

import javax.annotation.Nonnull;
import discord.bots.annoy.bot.audio.GuildMusicManager;
import discord.bots.annoy.bot.audio.PlayerManager;
import discord.bots.annoy.bot.command.CommandContext;
import net.dv8tion.jda.api.audio.AudioNatives;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.Guild;

import java.util.concurrent.atomic.AtomicBoolean;

public class AudioReceiveHandlerImpl implements AudioReceiveHandler {
  private AtomicBoolean audioChannelSilent;
  private Guild guild;
  private GuildMusicManager guildMusicManager;
  private static final String DONNIE_AUDIO_LINK = "https://www.youtube.com/watch?v=cTDALWmqhmg";
  private static final String DONNIE_1_HOUR_AUDIO_LINK = "https://www.youtube.com/watch?v=0XqDzxPY40A";


  public AudioReceiveHandlerImpl(CommandContext ctx) {
    this.audioChannelSilent = new AtomicBoolean(true);
    this.guild = ctx.getGuild();
    this.guildMusicManager = PlayerManager.getInstance().getMusicManager(guild);

    PlayerManager.getInstance().loadAndPlay(ctx.getTextChannel(), DONNIE_1_HOUR_AUDIO_LINK);
    AudioNatives.ensureOpus();
  }

  @Override
  public boolean canReceiveCombined() { return true; }

  public void handleCombinedAudio(@Nonnull CombinedAudio combinedAudio) {
    synchronized (this) {
      if(combinedAudio.getUsers().isEmpty()) {
        guildMusicManager.getScheduler().getPlayer().setPaused(true);
        // First moment of silence after speaking, lets turn off Donnies audio
//        if(audioChannelSilent.compareAndSet(false, true)) {
//          //final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
//
//          guildMusicManager.getScheduler().getPlayer().stopTrack();
//          guildMusicManager.getScheduler().getQueue().clear();
//
//        }
      } else {
        guildMusicManager.getScheduler().getPlayer().setPaused(false);
        //Someone is speaking for the first time, lets get Donnie to talk over them.
//        if(audioChannelSilent.compareAndSet(true, false)) {
//
//        }
      }
    }

  }


}

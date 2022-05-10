package discord.bots.annoy.bot.audio.handler;

import javax.annotation.Nonnull;

import com.sedmelluq.discord.lavaplayer.container.MediaContainerDescriptor;
import com.sedmelluq.discord.lavaplayer.container.MediaContainerProbe;
import com.sedmelluq.discord.lavaplayer.container.mp3.Mp3ContainerProbe;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import discord.bots.annoy.bot.audio.GuildMusicManager;
import discord.bots.annoy.bot.audio.PlayerManager;
import discord.bots.annoy.bot.command.CommandContext;
import net.dv8tion.jda.api.audio.AudioNatives;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import java.util.concurrent.atomic.AtomicBoolean;

public class AudioReceiveHandlerImpl implements AudioReceiveHandler {
  private AtomicBoolean audioChannelSilent;
  private Guild guild;
  private static final String DONNIE_AUDIO_LINK = "https://www.youtube.com/watch?v=cTDALWmqhmg";

  public AudioReceiveHandlerImpl(CommandContext ctx) {
    this.audioChannelSilent = new AtomicBoolean(true);
    this.guild = ctx.getGuild();

    AudioNatives.ensureOpus();
  }

  @Override
  public boolean canReceiveCombined() { return true; }

  public void handleCombinedAudio(@Nonnull CombinedAudio combinedAudio) {
    synchronized (this) {
      if(combinedAudio.getUsers().isEmpty()) {
        // First moment of silence after speaking, lets turn off Donnies audio
        if(audioChannelSilent.compareAndSet(false, true)) {
          final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);

          musicManager.getScheduler().getPlayer().stopTrack();
          musicManager.getScheduler().getQueue().clear();

        }
      } else {
        //Someone is speaking for the first time, lets get Donnie to talk over them.
        if(audioChannelSilent.compareAndSet(true, false)) {
//        Guild guild = combinedAudio.getUsers().get(0).getMutualGuilds().get(0);
          PlayerManager.getInstance().loadAndPlay(guild.getTextChannels().get(0), DONNIE_AUDIO_LINK);
        }
      }
    }

  }


}

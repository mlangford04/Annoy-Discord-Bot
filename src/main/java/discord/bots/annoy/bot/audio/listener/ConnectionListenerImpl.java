package discord.bots.annoy.bot.audio.listener;

import java.util.EnumSet;
import net.dv8tion.jda.api.audio.SpeakingMode;
import net.dv8tion.jda.api.audio.hooks.ConnectionListener;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

public class ConnectionListenerImpl implements ConnectionListener {

  @Override
  public void onPing(long l) {

  }

  @Override
  public void onStatusChange(@NotNull ConnectionStatus connectionStatus) {

  }

  @Override
  public void onUserSpeaking(@NotNull User user, boolean b) {
      System.out.println("Got here");
  }

  @Override
  public void onUserSpeaking(@NotNull User user, @NotNull EnumSet<SpeakingMode> modes) {
    ConnectionListener.super.onUserSpeaking(user, modes);
  }

//  @Override
//  public void onUserSpeaking(@NotNull User user, boolean speaking, boolean soundshare) {
//    ConnectionListener.super.onUserSpeaking(user, speaking, soundshare);
//  }
}

package discord.bots.annoy.bot.audio.handler;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;

public class AudioReceiveHandlerImpl implements AudioReceiveHandler {
  private boolean audioChannelSilent = true;

  @Override
  public boolean canReceiveCombined() { return true; }

//  public boolean canReceiveEncoded()
//  {
//    return true;
//  }
//  @Override
//  public boolean canReceiveUser()
//  {
//    return true;
//  }

  public void handleCombinedAudio(@Nonnull CombinedAudio combinedAudio) {
    if(combinedAudio.getUsers().isEmpty()) {
      audioChannelSilent = true;
    } else {
      System.out.println("Someone is speaking");
    }
  }


}

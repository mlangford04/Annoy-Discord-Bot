package discord.bots.DonnieThornberry.command.commands;

import discord.bots.DonnieThornberry.command.CommandContext;
import discord.bots.DonnieThornberry.command.ICommand;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class AnnoyCommand implements ICommand {

  @Override
  public void handle(CommandContext ctx) {
      if(joinChannel(ctx)) {
        //annoySpeakers(ctx);
      }
  }

  private boolean joinChannel(CommandContext ctx) {
    final TextChannel channel = ctx.getTextChannel();
    final Member self = ctx.getSelfMember();
    final GuildVoiceState selfVoiceState = self.getVoiceState();

    if (selfVoiceState.inAudioChannel()) {
      channel.sendMessage("I'm already in an audio channel").queue();
      return false;
    }

    final Member member = ctx.getMember();
    final GuildVoiceState memberVoiceState = member.getVoiceState();

    if (!memberVoiceState.inAudioChannel()) {
      channel.sendMessage("You need to be in an audio channel for this command to work").queue();
      return false;
    }

    final AudioManager audioManager = ctx.getGuild().getAudioManager();
    final AudioChannel memberChannel = memberVoiceState.getChannel();

    audioManager.openAudioConnection(memberChannel);
    channel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName()).queue();

    return true;
  }

  @Override
  public String getName() {
    return "annoy";
  }

  @Override
  public String getHelp() {
    return "Has Donnie Thornberry bot join channel and speak over everyone.";
  }

  @Override
  public String getAliases() {
    return "annoy";
  }
}

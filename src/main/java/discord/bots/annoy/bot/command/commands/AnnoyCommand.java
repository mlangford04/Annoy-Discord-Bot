package discord.bots.annoy.bot.command.commands;

import discord.bots.annoy.bot.audio.handler.AudioReceiveHandlerImpl;
import discord.bots.annoy.bot.command.CommandContext;
import discord.bots.annoy.bot.command.ICommand;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class AnnoyCommand implements ICommand {

  //TODO: Allow for more commands to add and remove names
  @Override
  public void handle(CommandContext ctx) {
    final TextChannel channel = ctx.getTextChannel();
    final Member self = ctx.getSelfMember();
    final GuildVoiceState selfVoiceState = self.getVoiceState();

    if (selfVoiceState.inAudioChannel()) {
      channel.sendMessage("I'm already in an audio channel").queue();
      return;
    }

    final Member member = ctx.getMember();
    final GuildVoiceState memberVoiceState = member.getVoiceState();

    if (!memberVoiceState.inAudioChannel()) {
      channel.sendMessage("You need to be in an audio channel for this command to work").queue();
      return;
    }

    final AudioManager audioManager = ctx.getGuild().getAudioManager();
    final AudioChannel memberChannel = memberVoiceState.getChannel();

    audioManager.setReceivingHandler(new AudioReceiveHandlerImpl(ctx));
    audioManager.openAudioConnection(memberChannel);

    channel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName()).queue();
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

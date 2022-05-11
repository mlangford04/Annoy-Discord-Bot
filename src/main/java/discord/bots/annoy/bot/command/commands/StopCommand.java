package discord.bots.annoy.bot.command.commands;

import discord.bots.annoy.bot.audio.GuildAudioManager;
import discord.bots.annoy.bot.audio.PlayerManager;
import discord.bots.annoy.bot.command.CommandContext;
import discord.bots.annoy.bot.command.ICommand;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.AudioManager;


public class StopCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getTextChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            channel.sendMessage("I need to be in a voice channel for this to work").queue();
            return;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inAudioChannel()) {
            channel.sendMessage("You need to be in an audio channel for this command to work").queue();
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
            return;
        }

        //Stop the audio
        GuildAudioManager guildAudioManager = PlayerManager.getInstance().getAudioManager(ctx.getGuild());
        guildAudioManager.getScheduler().getPlayer().stopTrack();
        guildAudioManager.getScheduler().getQueue().clear();

        //Leave the channel
        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        audioManager.closeAudioConnection();

    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getHelp() {
        return "Will stop Donnie from speaking.";
    }

    @Override
    public String getAliases() {
        return "stop";
    }
}

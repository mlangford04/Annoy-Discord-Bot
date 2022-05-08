package discord.bots.annoy.bot.command;

import java.util.List;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandContext {
  private final MessageReceivedEvent event;
  private final List<String> args;

  public CommandContext(MessageReceivedEvent event, List<String> args) {
    this.event = event;
    this.args = args;
  }

  public Guild getGuild() {
    return this.getEvent().getGuild();
  }

  /**
   * Returns the {@link net.dv8tion.jda.api.entities.Member author} of the message as member
   *
   * @return the {@link net.dv8tion.jda.api.entities.Member author} of the message as member
   */
  public Member getMember() {
    return this.getEvent().getMember();
  }

  /**
   * Returns the {@link net.dv8tion.jda.api.entities.Member member} in the guild for the currently logged in account
   *
   * @return the {@link net.dv8tion.jda.api.entities.Member member} in the guild for the currently logged in account
   */
  public Member getSelfMember() {
    return this.getGuild().getSelfMember();
  }

  /**
   * Returns the {@link net.dv8tion.jda.api.entities.TextChannel channel} that the message for this event was sent in
   *
   * @return the {@link net.dv8tion.jda.api.entities.TextChannel channel} that the message for this event was sent in
   */
  public TextChannel getTextChannel() {
    return this.getEvent().getTextChannel();
  }

  /**
   * Returns the {@link net.dv8tion.jda.api.entities.MessageChannel channel} that the message for this event was sent in
   *
   * @return the {@link net.dv8tion.jda.api.entities.MessageChannel channel} that the message for this event was sent in
   */
  public MessageChannel getMessageChannel() {
    return this.getEvent().getChannel();
  }

  public MessageReceivedEvent getEvent() {
    return this.event;
  }

  public List<String> getArgs() {
    return this.args;
  }
}

package discord.bots.annoy.bot.command;

import discord.bots.annoy.bot.command.commands.AnnoyCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import discord.bots.annoy.bot.command.commands.StopCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommandManager {
  private final List<ICommand> commands = new ArrayList<>();
  private static final Logger LOGGER = LoggerFactory.getLogger(CommandManager.class);

  public CommandManager() {
    addCommand(new AnnoyCommand());
    addCommand(new StopCommand());
  }

  private void addCommand(ICommand command) {
    boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(command.getName()));

    if (nameFound) {
      throw new IllegalArgumentException("A command with this name is already present");
    }

    commands.add(command);
  }

  private ICommand getCommand(String search) {
    String searchLower = search.toLowerCase();

    for (ICommand cmd : this.commands) {
      if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
        return cmd;
      }
    }

    return null;
  }

  public void handle(MessageReceivedEvent event, String content) {
    if(content.startsWith("!")) {
      String[] split = content.split(" ", 2);

      String command = split[0].substring(1);
      ICommand cmd = this.getCommand(command);

      if (cmd != null) {
        List<String> args = split.length > 1 ? Arrays.asList(split[1].split(",")) : null;

        event.getChannel().sendTyping().queue();
        LOGGER.info("Received '"+command+"' command from Discord.");
        CommandContext ctx = new CommandContext(event, args);

        cmd.handle(ctx);
      }
    }
  }
}

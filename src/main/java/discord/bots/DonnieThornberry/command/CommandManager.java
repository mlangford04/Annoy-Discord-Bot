package discord.bots.DonnieThornberry.command;

import discord.bots.DonnieThornberry.EventListener;
import discord.bots.DonnieThornberry.command.commands.AnnoyCommand;
import java.util.ArrayList;
import java.util.List;
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
    //TODO: handle event with arguments
    if(content.startsWith("!"))
      content = content.substring(1,content.length());
    ICommand cmd = this.getCommand(content);

    if (cmd != null) {
      event.getChannel().sendTyping().queue();
      List<String> args = null;

      LOGGER.info("Recieved '"+content+"' command from Discord.");
      CommandContext ctx = new CommandContext(event, args);

      cmd.handle(ctx);
    }
  }
}

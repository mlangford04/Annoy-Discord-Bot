package discord.bots.DonnieThornberry;

import discord.bots.DonnieThornberry.command.CommandManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EventListener extends ListenerAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);
  private final CommandManager commandManager = new CommandManager();

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    {
      Message message = event.getMessage();
      User author = message.getAuthor();
      String content = message.getContentRaw();
      Guild guild = event.getGuild();

      // Ignore message if bot
      if (author.isBot())
        return;

      // We only want to handle message in Guilds
      if (!event.isFromGuild()) {
        return;
      }

      commandManager.handle(event, content);
    }
  }

}

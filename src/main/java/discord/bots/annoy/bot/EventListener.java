package discord.bots.annoy.bot;

import discord.bots.annoy.bot.command.CommandManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListener extends ListenerAdapter {
  private final CommandManager commandManager = new CommandManager();

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    {
      Message message = event.getMessage();
      User author = message.getAuthor();
      String content = message.getContentRaw();

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

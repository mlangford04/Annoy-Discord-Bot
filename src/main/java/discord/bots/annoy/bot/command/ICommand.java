package discord.bots.annoy.bot.command;

public interface ICommand {
  void handle(CommandContext ctx);

  String getName();

  String getHelp();

  String getAliases();
}

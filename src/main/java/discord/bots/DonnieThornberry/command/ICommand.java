package discord.bots.DonnieThornberry.command;

public interface ICommand {
  void handle(CommandContext ctx);

  String getName();

  String getHelp();

  String getAliases();
}

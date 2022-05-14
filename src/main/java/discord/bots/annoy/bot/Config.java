package discord.bots.annoy.bot;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("./src/main/resources")
            .filename("env")
            .load();

    public static String get(String key) {
        return dotenv.get(key.toUpperCase());
    }
}

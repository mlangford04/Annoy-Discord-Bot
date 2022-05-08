package discord.bots.annoy.bot;

import club.minnced.opus.util.OpusLibrary;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tomp2p.opuswrapper.Opus;

@SpringBootApplication
public class DonnieThornberryApplication {

	public static void main(String[] args) {
		//OpusLibrary.loadFrom("/Users/michaellangford/Documents/Projects/utils/opus-java/natives/src/main/resources/natives/darwin/libopus.dylib");
		SpringApplication.run(DonnieThornberryApplication.class, args);
	}

}
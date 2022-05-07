package discord.bots.DonnieThornberry;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DonnieThornberryApplication {
	public static JDA jda;

	public static void main(String[] args) throws LoginException {
		new DonnieBot();
		SpringApplication.run(DonnieThornberryApplication.class, args);
	}
//
//	@Override
//	public void run

}

//OTcyNTczOTIyODQ5NTk1NDYy.GqrHeu.9XVO5skXme3gnmgWSoBD2LCkZ9owibvfcjLMr4

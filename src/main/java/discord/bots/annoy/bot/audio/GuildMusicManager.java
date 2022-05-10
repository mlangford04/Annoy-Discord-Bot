package discord.bots.annoy.bot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import discord.bots.annoy.bot.audio.handler.AudioSendHandlerImpl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuildMusicManager {
    private final AudioPlayer audioPlayer;

    private final TrackScheduler scheduler;

    private final AudioSendHandlerImpl sendHandler;

    public GuildMusicManager(AudioPlayerManager manager) {
        this.audioPlayer = manager.createPlayer();
        this.scheduler = new TrackScheduler(this.audioPlayer);
        this.audioPlayer.addListener(this.scheduler);
        this.sendHandler = new AudioSendHandlerImpl(this.audioPlayer);
    }
}

package com.seventh.translate.commands;

import com.seventh.translate.utilities.EmbedTitleEnum;
import com.seventh.translate.utilities.LanguageEnum;
import com.seventh.translate.services.MyMemoryService;
import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.MessageInteractionEvent;
import discord4j.core.object.command.Interaction;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.InteractionFollowupCreateMono;
import discord4j.rest.util.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Optional;

@Component
public class JapaneseCommand implements MessageCommand {
    @Autowired
    private MyMemoryService myMemoryService;

    private InteractionFollowupCreateMono handleTranslation(MessageInteractionEvent event) throws URISyntaxException, IOException, InterruptedException {
        Interaction interaction = event.getInteraction();
        Optional<Snowflake> guildId = interaction.getGuildId();
        Long channel_id = interaction.getChannelId().asLong();

        Message resolvedMessage = event.getResolvedMessage();
        Optional<User> author = resolvedMessage.getAuthor();
        String messageContent = resolvedMessage.getContent();

        long message_id = event.getTargetId().asLong();
        Long guild_identifier = guildId.get().asLong();
        String profilePic = author.get().getAvatarUrl();
        String username = author.get().getUsername();

        boolean isSameLang = false;
        String findingLanguage = myMemoryService.findLanguage(messageContent);
        String detectedLanguage = LanguageEnum.valueOf(findingLanguage).langCode.toLowerCase();

        if (detectedLanguage.isBlank()) {
            return event.createFollowup("This language is not supported.").withEphemeral(true);
        }
        String langPair = detectedLanguage + "|ja";
        if (langPair.equals("ja|ja")) {
            isSameLang = true;
        }
        Optional<Instant> editedTimestamp = resolvedMessage.getEditedTimestamp();
        String cache_message_id = guild_identifier + "_" + channel_id + "_" + message_id + "_ja";

        String translatedText = myMemoryService.getTranslation(messageContent, langPair, isSameLang, detectedLanguage, cache_message_id, editedTimestamp);
        return event
                .createFollowup()
                .withEmbeds(
                        EmbedCreateSpec
                                .builder()
                                .color(Color.DISCORD_WHITE)
                                .author(username,"https://discord.com/channels/" + guild_identifier + "/" + channel_id + "/" + message_id, profilePic)
                                .title(EmbedTitleEnum.JA.langTitle)
                                .url("https://discord.com/channels/" + guild_identifier + "/" + channel_id + "/" + message_id)
                                .description(translatedText)
                                .build()
                )
                .withEphemeral(true);
    }
    @Override
    public String getName(){
        return "やくする|JP";
    }

    @Override
    public Mono<Message> handle(MessageInteractionEvent event) {

        return event.deferReply()
                .withEphemeral(true)
                .log()
                .then(Mono.defer(() -> {
                    try {
                        return handleTranslation(event);
                    } catch (URISyntaxException | IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }));
    }
}

package com.seventh.translate.commands;

import com.seventh.translate.utilities.LanguageEnum;
import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.MessageInteractionEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public interface MessageCommand {
    String getName();
    Mono<Message> handle(MessageInteractionEvent event) throws URISyntaxException, IOException, InterruptedException;
}
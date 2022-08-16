package com.seventh.translate.listeners;

import com.seventh.translate.commands.MessageCommand;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.MessageInteractionEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

@Component
public class MessageCommandListener {
    private final Collection<MessageCommand> commands;

    public MessageCommandListener(List<MessageCommand> messageCommands, GatewayDiscordClient client) {
        commands = messageCommands;

        client.on(MessageInteractionEvent.class, this::handle).subscribe();
    }

    public Mono<Message> handle(MessageInteractionEvent event) {
        return Flux.fromIterable(commands)
                .filter(command -> command.getName().equals(event.getCommandName()))
                .next()
                .flatMap(command -> {
                    try {
                        return command.handle(event);
                    } catch (URISyntaxException | IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }
}

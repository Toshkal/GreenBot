package ru.home.button;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.service.BotMessageService;
@Component
@RequiredArgsConstructor
public class StartButton implements Button{

    private final BotMessageService botMessageService;
    public final static String MESSAGE = "давай начнем";

    @Override
    public void execute(Update update) {
    Long chatId = update.getMessage().getChatId();

    botMessageService.botMessage(chatId, MESSAGE);
    }


}

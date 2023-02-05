package ru.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.home.bot.TelegramBot;

import static java.util.Arrays.asList;
import static ru.home.constant.ButtonName.*;

@Service
public class AnswerMessage implements BotMessageService {

    private TelegramBot bot;

    @Lazy
    @Autowired
    public AnswerMessage(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void botMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException exception) {
            exception.printStackTrace();
        }
    }
}


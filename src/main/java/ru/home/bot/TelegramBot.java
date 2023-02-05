package ru.home.bot;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.home.service.AnswerMessage;

//класс для взаимодействия с ботом
//@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    String botName;
    @Value("${bot.token}")
    String botToken;
    AnswerMessage answerMessage;

//    @Autowired
//    public TelegramBot(AnswerMessage answerMessage) {
//        this.answerMessage = answerMessage;
//    }

    @Override
    @SneakyThrows// ломбок обход try
    public void onUpdateReceived(Update update) {                       //Метод для ответа на введенную информацию
        if (update.hasCallbackQuery()){
            try {
                handleCallback(update.getCallbackQuery());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        Message message = update.getMessage();
        if (message.getText().equals("/start")){
            (message);
        }
        createButtons(message);
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

package com.company;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getFrom().getFirstName() + ": " + update.getMessage().getText());
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

        if(update.getMessage().getText().toLowerCase().equals("how are you?"))
        {
            sendMessage.setText("I'm Fine");

        }
        else
        {
            sendMessage.setText("Hello " + "\n" + update.getMessage().getText());

        }

       //


        //+ update.getMessage().getFrom().getFirstName()
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return "1081899825:AAHcnquLUrffhkqU5LeS4dyAzZ64ZbSbA-s";
    }
}

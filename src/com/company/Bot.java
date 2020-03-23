package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

        sendMessage.setText("Hello enter the keyword!");
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        System.out.println(update.getMessage().getFrom().getFirstName() + ": " + update.getMessage().getText());

        String str = update.getMessage().getText().toLowerCase();

        String searchURL = "https://www.google.com/search" + "?q=" + str;

        sendMessage.setText(searchURL);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        //without proper User-Agent, we will get 403 error
        Document doc = null;
        try {
            doc = Jsoup.connect(searchURL).userAgent("Chrome/41.0.2228.0").get();
            sendMessage.setText("here 1");
            try {
                sendMessage(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements results = doc.select("h3.r > a");
        sendMessage.setText("here 2");

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();
            sendMessage.setText("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
            try {
                sendMessage(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

//        try {
//            sendMessage(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        if(update.getMessage().getText().toLowerCase().equals(str))
//        {
//            sendMessage.setText("I'm Fine");
//
//        }
//        else
//        {
//            sendMessage.setText("Hello " + "\n" + update.getMessage().getText());
//
//        }

        //


        //+ update.getMessage().getFrom().getFirstName()


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
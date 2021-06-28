package com.telebot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
    public class AppMain {
        public static void main(String[] args) {
            try {
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                telegramBotsApi.registerBot(new introbot());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }
    /*public String getData(MongoCollection<Document> coll)
    {
        Consumer<Document> printConsumer = new Consumer<Document>() {
            @Override
            public void accept(final Document document) {
                System.out.println(document.toString());
            }
        };

        coll.find().forEach(printConsumer);

    }
}*/

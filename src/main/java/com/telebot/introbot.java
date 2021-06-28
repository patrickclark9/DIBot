package com.telebot;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;

public class introbot extends TelegramLongPollingBot {
    MongoDBClient db = new MongoDBClient();

    config cfg = new config();

    String botToken = cfg.getBotToken();
    String botName = cfg.getBotName();

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String commands = update.getMessage().getText();
        SendMessage message = new SendMessage();
        if ("/start".equals(commands)) {
            message.setText("Ciao, benvenuto su DIBot!" + "Qui hai accesso rapido a tutte le informazioni presenti"
                    + "sul sito del dipartimento di Informatica dell'Università degli Studi di Bari."
                    + "Che informazioni vuoi avere?");
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            row.add("Informatica - Sbocchi Lavorativi");
            row.add("Informatica - Primo Piano");
            keyboard.add(row);
            row = new KeyboardRow();
            row.add("Informatica - Didattica");
            row.add("Informatica - Notizie");
            keyboard.add(row);
            row = new KeyboardRow();
            row.add("Informatica - Ulteriori Informazioni");
            row.add("Informatica - Contatti");
            keyboard.add(row);
            row = new KeyboardRow();
            row.add("Informatica - Eventi");
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(keyboardMarkup);

        } else if ("Informatica - Sbocchi Lavorativi".equals(commands)) {
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText("Ecco le ultime notizie sugli sbocchi lavorativi:");
            // send Initial Message
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            // Init DB Client

            // Iterate through DB and return all fields in named Collection
            try (MongoCursor<Document> cursor = db.getCollection("Job").find().projection(fields(excludeId()))
                    .iterator()) {
                while (cursor.hasNext()) {
                    JSONObject obj = new JSONObject(cursor.next().toJson());
                    String res = obj.getString("Title") + "\n" + obj.getString("Link");
                    message.setText(res);
                    if (cursor.hasNext()) {
                        try {
                            execute(message);

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        } else if ("Informatica - Notizie".equals(commands)) {
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText("Ecco le ultime Notizie dal Dipartimento di Informatica:");

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            try (MongoCursor<Document> cursor = db.getCollection("Notizie").find().projection(fields(excludeId()))
                    .iterator()) {
                while (cursor.hasNext()) {
                    JSONObject obj = new JSONObject(cursor.next().toJson());
                    String res = obj.getString("Title") + "\n" + obj.getString("Link");
                    message.setText(res);
                    if (cursor.hasNext()) {
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else if ("Informatica - Contatti".equals(commands)) {
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText("Ecco i contatti dei referenti per il Dipartimento di Informatica:");

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            try (MongoCursor<Document> cursor = db.getCollection("Contatti").find().projection(fields(excludeId()))
                    .iterator()) {
                while (cursor.hasNext()) {
                    JSONObject obj = new JSONObject(cursor.next().toJson());
                    String res = obj.getString("Title") + "\n" + obj.getString("Link");
                    message.setText(res);
                    if (cursor.hasNext()) {
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } else if ("Informatica - Ulteriori Informazioni".equals(commands)) {
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText("Ecco ulteriori informazioni sul Dipartimento di Informatica:");

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            try (MongoCursor<Document> cursor = db.getCollection("Ultinfo").find().projection(fields(excludeId()))
                    .iterator()) {
                while (cursor.hasNext()) {
                    JSONObject obj = new JSONObject(cursor.next().toJson());
                    String res = obj.getString("Title") + "\n" + obj.getString("Link");
                    message.setText(res);
                    if (cursor.hasNext()) {
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } else if ("Informatica - Eventi".equals(commands)) {
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText("Ecco gli ultimi eventi del Dipartimento di Informatica:");

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            try (MongoCursor<Document> cursor = db.getCollection("Eventi").find().projection(fields(excludeId()))
                    .iterator()) {
                while (cursor.hasNext()) {
                    JSONObject obj = new JSONObject(cursor.next().toJson());
                    String res = obj.getString("Title") + "\n" + obj.getString("Link");
                    message.setText(res);
                    if (cursor.hasNext()) {
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } else if ("Informatica - Primo Piano".equals(commands)) {
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText("Ecco le notizie in primo piano del Dipartimento di Informatica:");

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            try (MongoCursor<Document> cursor = db.getCollection("Primo piano").find().projection(fields(excludeId()))
                    .iterator()) {
                while (cursor.hasNext()) {
                    JSONObject obj = new JSONObject(cursor.next().toJson());
                    String res = obj.getString("Title") + "\n" + obj.getString("Link");
                    message.setText(res);
                    if (cursor.hasNext()) {
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } else if ("Informatica - Didattica".equals(commands)) {
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText("Ecco le informazioni sulla didattica del Dipartimento di Informatica:");

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            try (MongoCursor<Document> cursor = db.getCollection("Didattica").find().projection(fields(excludeId()))
                    .iterator()) {
                while (cursor.hasNext()) {
                    JSONObject obj = new JSONObject(cursor.next().toJson());
                    String res = obj.getString("Title") + "\n" + obj.getString("Link");
                    message.setText(res);
                    if (cursor.hasNext()) {
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } else {
            message.setText("Non ho capito bene; usa /start per avviare la tastiera");
        }
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}

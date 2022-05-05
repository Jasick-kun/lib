package kz.alizhan.lib.Controllers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class HelloCommand {
    public static SendMessage helloCom(Update update) {
        SendMessage sendMessage = (SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("Здравствуйте, " + getName(update) + ". Вас приветствует библиотека №5")
                .build());
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Поиск книги"));
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return sendMessage;
    }

    private static String getName(Update update) {
        if (update.getMessage().getFrom().getFirstName() != null) {
            return update.getMessage().getFrom().getFirstName();
        } else return update.getMessage().getFrom().getLastName();
    }
}

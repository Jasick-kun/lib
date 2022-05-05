package kz.alizhan.lib.Controllers;

import kz.alizhan.lib.Constants.BookState;
import kz.alizhan.lib.Entity.Book;
import kz.alizhan.lib.Repository.BookRepository;
import kz.alizhan.lib.Repository.DialogRepository;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class SearchCommand extends Bot {
        private final BookRepository bookRepository;
        private final DialogRepository dialogRepository;



    public  SendMessage search(Update update){
        SendMessage sendMessage = SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("Выберите тип поиска")
                .build();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Поиск по названию"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Поиск по автору"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("Поиск по году выпуска"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return sendMessage;
    }
    public void searchByAuthor(Update update) {
        List<Book> books = new ArrayList<>();

        books = bookRepository.findBooksByAuthor(update.getMessage().getText());
        for (Book book : books) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Название : " + book.getName());
            stringBuilder.append("\nАвтор : " + book.getAuthor());
            stringBuilder.append("\nГод выпуска : " + book.getYearOfIssue().toString());
            String status = "";
            SendPhoto sendPhoto = new SendPhoto();
            if (book.getStatus().equals(BookState.BOOKED)) {
                status = "Забронировано";
            } else if (book.getStatus().equals(BookState.IN_STOCK)) {
                status = "В наличии";
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText("Забронировать");
                List<InlineKeyboardButton> list = new ArrayList<>();
                list.add(inlineKeyboardButton);
                inlineKeyboardButton.setCallbackData(book.getId().toString());
                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                rowList.add(list);
                inlineKeyboardMarkup.setKeyboard(rowList);
                sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
            } else if (book.getStatus().equals(BookState.ON_THE_HANDS)) {
                status = "На руках";

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText("Забронировать");
                List<InlineKeyboardButton> list = new ArrayList<>();
                list.add(inlineKeyboardButton);
                inlineKeyboardButton.setCallbackData(book.getId().toString());
                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                rowList.add(list);
                inlineKeyboardMarkup.setKeyboard(rowList);
                sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
            }
            sendPhoto.setChatId(update.getMessage().getChatId().toString());
            sendPhoto.setPhoto(new InputFile(new File(System.getProperty("user.dir") + "\\Content\\" + book.getName() + ".jpg")));

            stringBuilder.append("\nСтатус : " + status);
            sendPhoto.setCaption(stringBuilder.toString());


            dialogRepository.deleteDialogByChatId(update.getMessage().getChatId());
            executeAsync(sendPhoto);
        }
    }
    public void searchByName(Update update) throws TelegramApiException, IOException {
        List<Book> books = new ArrayList<>();

        books = bookRepository.findBooksByName(update.getMessage().getText());
        for (Book book: books) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Название : " + book.getName());
            stringBuilder.append("\nАвтор : " + book.getAuthor());
            stringBuilder.append("\nГод выпуска : "+book.getYearOfIssue().toString());
            String status = "";
            SendPhoto sendPhoto = new SendPhoto();
            if(book.getStatus().equals(BookState.BOOKED)){
                status = "Забронировано";
            }
            else if(book.getStatus().equals(BookState.IN_STOCK)){
                status = "В наличии";
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText("Забронировать");
                List<InlineKeyboardButton> list = new ArrayList<>();
                list.add(inlineKeyboardButton);
                inlineKeyboardButton.setCallbackData(book.getId().toString());
                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                rowList.add(list);
                inlineKeyboardMarkup.setKeyboard(rowList);
                sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
            }

            else if(book.getStatus().equals(BookState.ON_THE_HANDS)){
                status = "На руках";

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText("Забронировать");
                List<InlineKeyboardButton> list = new ArrayList<>();
                list.add(inlineKeyboardButton);
                inlineKeyboardButton.setCallbackData(book.getId().toString());
                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                rowList.add(list);
                inlineKeyboardMarkup.setKeyboard(rowList);
                sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
            }
            sendPhoto.setChatId(update.getMessage().getChatId().toString());
            sendPhoto.setPhoto(new InputFile(new File(System.getProperty("user.dir")+"\\Content\\"+book.getName()+".jpg")));

            stringBuilder.append("\nСтатус : "+status);
            sendPhoto.setCaption(stringBuilder.toString());


            dialogRepository.deleteDialogByChatId(update.getMessage().getChatId());
            executeAsync(sendPhoto);


        }
    }
    public void searchByYear(Update update) throws TelegramApiException, IOException {
        List<Book> books = new ArrayList<>();

        books = bookRepository.findBooksByYearOfIssue(Integer.parseInt(update.getMessage().getText()));
        for (Book book: books) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Название : " + book.getName());
            stringBuilder.append("\nАвтор : " + book.getAuthor());
            stringBuilder.append("\nГод выпуска : "+book.getYearOfIssue().toString());
            String status = "";
            SendPhoto sendPhoto = new SendPhoto();
            if(book.getStatus().equals(BookState.BOOKED)){
                status = "Забронировано";
            }
            else if(book.getStatus().equals(BookState.IN_STOCK)){
                status = "В наличии";
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText("Забронировать");
                List<InlineKeyboardButton> list = new ArrayList<>();
                list.add(inlineKeyboardButton);
                inlineKeyboardButton.setCallbackData(book.getId().toString());
                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                rowList.add(list);
                inlineKeyboardMarkup.setKeyboard(rowList);
                sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
            }

            else if(book.getStatus().equals(BookState.ON_THE_HANDS)){
                status = "На руках";

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText("Забронировать");
                List<InlineKeyboardButton> list = new ArrayList<>();
                list.add(inlineKeyboardButton);
                inlineKeyboardButton.setCallbackData(book.getId().toString());
                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                rowList.add(list);
                inlineKeyboardMarkup.setKeyboard(rowList);
                sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
            }
            sendPhoto.setChatId(update.getMessage().getChatId().toString());
            sendPhoto.setPhoto(new InputFile(new File(System.getProperty("user.dir")+"\\Content\\"+book.getName()+".jpg")));

            stringBuilder.append("\nСтатус : "+status);
            sendPhoto.setCaption(stringBuilder.toString());


            dialogRepository.deleteDialogByChatId(update.getMessage().getChatId());
            executeAsync(sendPhoto);


        }
    }



}

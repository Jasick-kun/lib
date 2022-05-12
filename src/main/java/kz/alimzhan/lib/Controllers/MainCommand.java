package kz.alimzhan.lib.Controllers;

import kz.alimzhan.lib.Entity.Book;
import kz.alimzhan.lib.Entity.Dialog;
import kz.alimzhan.lib.Repository.BookRepository;
import kz.alimzhan.lib.Repository.DialogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MainCommand extends Bot {

    private final DialogRepository dialogRepository;
    private final BookRepository bookRepository;


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            bookRepository.reserveBook(Long
                    .parseLong(update.getCallbackQuery().getData()), update
                    .getCallbackQuery().getFrom().getFirstName() + " " + update.getCallbackQuery().getFrom().getLastName());
            try {
                Book book = new Book();
                book = bookRepository.findById(Long.parseLong(update.getCallbackQuery().getData())).get();
                execute(SendMessage.builder().chatId(update.getCallbackQuery().getMessage().getChatId().toString()).text("Спасибо за обращение.\n" +
                        "Книга успешно забронирована. \n" +
                        "Можете забрать книгу по адресу " + book.getLibrary().getAddress() + " \n" +
                        "в течений двух дней сказав своё имя из телеграмм").build());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (update.getMessage() != null) {
            SendMessage sendMessage = SendMessage.builder()
                    .chatId(update.getMessage().getChatId().toString())
                    .text("Такой команды не существует")
                    .build();
            Optional<Integer> dialogId = dialogRepository.getDialogId(update.getMessage().getChatId());

            if (update.getMessage().getText().equals("/back")) {
                dialogRepository.deleteDialogByChatId(update.getMessage().getChatId());
                try {
                    SearchCommand searchCommand = new SearchCommand(bookRepository, dialogRepository);
                    execute(searchCommand.search(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (!dialogId.isPresent()) {
                if (update.getMessage().getText().equals("/start") || update.getMessage().getText().equals("/hello")) {
                    sendMessage = HelloCommand.helloCom(update);

                } else if (update.getMessage().getText().equals("Поиск книги")) {
                    SearchCommand searchCommand = new SearchCommand(bookRepository, dialogRepository);
                    sendMessage = searchCommand.search(update);
                } else if (update.getMessage().getText().equals("Поиск по названию")) {
                    Dialog dialog = new Dialog();
                    dialog.setDialogId(1);
                    dialog.setChatId(update.getMessage().getChatId());
                    dialogRepository.save(dialog);
                    sendMessage.setText("Введите название книги");
                } else if (update.getMessage().getText().equals("Поиск по автору")) {
                    Dialog dialog = new Dialog();
                    dialog.setDialogId(2);
                    dialog.setChatId(update.getMessage().getChatId());
                    dialogRepository.save(dialog);
                    sendMessage.setText("Введите имя автора");
                } else if (update.getMessage().getText().equals("Поиск по году выпуска")) {
                    Dialog dialog = new Dialog();
                    dialog.setDialogId(3);
                    dialog.setChatId(update.getMessage().getChatId());
                    dialogRepository.save(dialog);
                    sendMessage.setText("Введите год выпуска");
                }
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            } else {
                if (dialogId.get() == 1) {
                    SearchCommand searchCommand = new SearchCommand(bookRepository, dialogRepository);
                    try {
                        searchCommand.searchByName(update);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (dialogId.get() == 2) {
                    SearchCommand searchCommand = new SearchCommand(bookRepository, dialogRepository);
                    try {
                        searchCommand.searchByAuthor(update);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (dialogId.get() == 3) {
                    SearchCommand searchCommand = new SearchCommand(bookRepository, dialogRepository);
                    try {
                        searchCommand.searchByYear(update);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }
}


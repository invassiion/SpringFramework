package com.merion.spring.book.service;

import com.merion.spring.book.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookService {
    static List<BookEntity> bookStorage = new ArrayList<>();

    public BookService() {
        fillStorage();
    }
    public void  fillStorage() {
        Random random = new SecureRandom();
        for (int i = 0; i < 100; i++) {
            BookEntity book = new BookEntity();
            book.setId(i);
            book.setTitle("Book #" + random.nextInt(100,999));
            book.setDescription("Не следует, однако, забывать, что понимание сути ресурсосберегающих технологий" +
                    " в значительной степени обусловливает важность переосмысления внешнеэкономических политик. " +
                    "С учётом сложившейся международной обстановки, базовый вектор развития говорит о возможностях соответствующих условий активизации." +
                    " Внезапно, многие известные личности объявлены нарушающими общечеловеческие нормы этики и морали.");
            bookStorage.add(book);
        }
    }

    public List<BookEntity> allBook(){
        return bookStorage;
    }

    public Optional<BookEntity> byId(Integer id) {
        return bookStorage.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }
}

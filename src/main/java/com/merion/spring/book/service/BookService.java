package com.merion.spring.book.service;

import com.merion.spring.book.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

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

    public BookEntity create(String title, String description) {
        BookEntity book = new BookEntity();
        book.setId(bookStorage.size());
        book.setTitle(title);
        book.setDescription(description);
        bookStorage.add(book);
        return book;
    }

    public Optional<BookEntity> edit(BookEntity book) {
        Optional<BookEntity> oldBookOptional  = byId(book.getId());
        if (oldBookOptional.isEmpty()) {
            return Optional.empty();
        }

        BookEntity oldBookEntity = oldBookOptional.get();
        oldBookEntity.setTitle(book.getTitle());
        oldBookEntity.setDescription(book.getDescription());
        return Optional.of(oldBookEntity);
    }


    public Boolean delete(Integer id) {
        Optional<BookEntity> book = byId(id);
        if (book.isEmpty()) {
            return false;
        }

        bookStorage.remove(book.get());
        return true;
    }

    public Optional<BookEntity> editPart(Integer id, Map<String, String> fields) {
        Optional<BookEntity> optionalBookEntity = byId(id);
        if (optionalBookEntity.isEmpty()) return  Optional.empty();

        BookEntity book = optionalBookEntity.get();

        for (String key : fields.keySet() ) {
            switch (key) {
                case "title" -> book.setTitle(fields.get(key));
                case "description" -> book.setDescription(fields.get(key));
            }
        }

        return Optional.of(book);
    }
}

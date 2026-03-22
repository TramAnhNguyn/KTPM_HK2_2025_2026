package iuh.fit.se;

import iuh.fit.se.factoryMethod.LibraryBook;
import java.util.ArrayList;
import java.util.List;

// This is a singleton class.
// It ensures that only one instance of the Library class exists and provides a global point of access to it.
public class Library {
    private static Library instance;
    private List<LibraryBook> books = new ArrayList<>();

    private Library() {}

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(LibraryBook book) {
        books.add(book);
    }

    public List<LibraryBook> getBooks() {
        return books;
    }
}

package iuh.fit.se;

import iuh.fit.se.decoratorBorrow.BasicBorrow;
import iuh.fit.se.decoratorBorrow.Borrow;
import iuh.fit.se.decoratorBorrow.ExtendTime;
import iuh.fit.se.factoryMethod.BookFactory;
import iuh.fit.se.observerNotification.Notification;
import iuh.fit.se.observerNotification.User;
import iuh.fit.se.strategySearch.SearchByTitle;
import iuh.fit.se.strategySearch.SearchStrategy;
import iuh.fit.se.factoryMethod.LibraryBook;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Singleton + Factory
        Library lib = Library.getInstance();
        LibraryBook b1 = BookFactory.createBook("paper", "Java", "A");
        LibraryBook b2 = BookFactory.createBook("ebook", "React", "B");

        lib.addBook(b1);
        lib.addBook(b2);

        // Strategy
        SearchStrategy search = new SearchByTitle();
        System.out.println("Search result: " + search.search(lib.getBooks(), "Java"));

        // Observer
        Notification n = new Notification();
        n.addObserver(new User());
        n.notifyAllObservers("Có sách mới!");

        // Decorator
        Borrow borrow = new ExtendTime(new BasicBorrow());
        borrow.borrow();
    }
}

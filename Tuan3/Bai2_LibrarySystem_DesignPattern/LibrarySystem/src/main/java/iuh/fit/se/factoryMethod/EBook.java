package iuh.fit.se.factoryMethod;

public class EBook extends LibraryBook {
    public EBook(String t, String a) {
        super(t, a);
    }

    public void display() {
        System.out.println("EBook: " + title);
    }
}

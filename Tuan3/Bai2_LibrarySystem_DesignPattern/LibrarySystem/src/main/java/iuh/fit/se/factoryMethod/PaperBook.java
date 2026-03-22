package iuh.fit.se.factoryMethod;

public class PaperBook extends LibraryBook {
    public PaperBook(String t, String a) {
        super(t, a);
    }

    public void display() {
        System.out.println("Paper Book: " + title);
    }
}

package iuh.fit.se.factoryMethod;

public class AudioBook extends LibraryBook {
    public AudioBook(String t, String a) {
        super(t, a);
    }

    public void display() {
        System.out.println("Audio Book: " + title);
    }
}

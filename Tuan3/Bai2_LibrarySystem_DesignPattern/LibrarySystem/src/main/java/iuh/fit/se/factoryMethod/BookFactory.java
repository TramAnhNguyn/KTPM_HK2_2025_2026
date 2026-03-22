package iuh.fit.se.factoryMethod;

public class BookFactory {
    public static LibraryBook createBook(String type, String t, String a) {
        switch (type) {
            case "paper": return new PaperBook(t, a);
            case "ebook": return new EBook(t, a);
            case "audio": return new AudioBook(t, a);
        }
        return null;
    }
}

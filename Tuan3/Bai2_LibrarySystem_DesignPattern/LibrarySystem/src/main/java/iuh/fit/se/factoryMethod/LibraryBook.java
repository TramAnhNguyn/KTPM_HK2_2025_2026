package iuh.fit.se.factoryMethod;

public abstract class LibraryBook {
    protected String title;
    protected String author;

    public LibraryBook(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Thêm hàm getTitle để các package khác có thể lấy thông tin
    public String getTitle() {
        return title;
    }

    public abstract void display();
}

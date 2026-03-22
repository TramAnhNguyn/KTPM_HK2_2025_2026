package iuh.fit.se.strategySearch;
import iuh.fit.se.factoryMethod.LibraryBook;
import java.util.ArrayList;
import java.util.List;

public class SearchByTitle implements SearchStrategy {
    public List<LibraryBook> search(List<LibraryBook> books, String keyword) {
        List<LibraryBook> result = new ArrayList<>();

        for (LibraryBook b : books) {
            if (b.getTitle().contains(keyword)) {
                result.add(b);
            }
        }
        return result;
    }
}

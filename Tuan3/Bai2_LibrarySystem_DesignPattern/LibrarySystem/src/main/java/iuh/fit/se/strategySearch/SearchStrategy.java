package iuh.fit.se.strategySearch;
import iuh.fit.se.factoryMethod.LibraryBook;
import java.util.List;

public interface SearchStrategy {
    List<LibraryBook> search(List<LibraryBook> books, String keyword);
}

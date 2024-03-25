package serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.example.entity.Book;
import org.example.service.BookService;
import org.junit.jupiter.api.Test;

public class BookServiceTest {
    private BookService service = mock(BookService.class);

    @Test
    public void should_successfully_add() {
        String bookName = "Dracula";
        String authorName = "Bram Stocker";
        service.addBook(bookName, authorName);
        verify(service, times(1)).addBook(bookName, authorName);
    }

    @Test
    public void should_successfully_get() {
        ArrayList<Book> expected = new ArrayList<>();
        Book book = Book.builder().id(1).name("Dracula").autor_id(1).build();
        expected.add(book);

        when(service.getBooks()).thenReturn(expected);

        ArrayList<Book> actual = service.getBooks();

        assertEquals(expected, actual);
    }

    @Test
    public void should_successfully_delete() {
        String name = "Bram Stocker";
        service.deleteBook(name);
        verify(service, times(1)).deleteBook(name);
    }

    @Test
    public void should_successfully_update() {
        String name = "Dracula";
        String newName = "Vladislav";
        service.updateBookName(name, newName);
        verify(service, times(1)).updateBookName(name, newName);
    }
}

package controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.example.controller.BookController;
import org.example.entity.Book;
import org.junit.jupiter.api.Test;

public class BookControllerTest {
    private BookController controller = mock(BookController.class);

    @Test
    public void should_successfully_add() {
        String bookName = "Dracula";
        String authorName = "Bram Stocker";
        controller.addBook(bookName, authorName);
        verify(controller, times(1)).addBook(bookName, authorName);
    }

    @Test
    public void should_successfully_get() {
        ArrayList<Book> expected = new ArrayList<>();
        Book book = Book.builder().id(1).name("Dracula").autor_id(1).build();
        expected.add(book);

        when(controller.getBooks()).thenReturn(expected);

        ArrayList<Book> actual = controller.getBooks();

        assertEquals(expected, actual);
    }

    @Test
    public void should_successfully_delete() {
        String name = "Bram Stocker";
        controller.deleteBook(name);
        verify(controller, times(1)).deleteBook(name);
    }

    @Test
    public void should_successfully_update() {
        String name = "Dracula";
        String newName = "Vladislav";
        controller.updateBookName(name, newName);
        verify(controller, times(1)).updateBookName(name, newName);
    }
}

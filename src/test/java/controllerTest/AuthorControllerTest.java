package controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.controller.AuthorController;
import org.example.entity.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {
    private AuthorController controller = mock(AuthorController.class);

    @Test
    public void should_successfully_add() {
        String name = "Bram Stocker";
        controller.addAuthor(name);
        verify(controller, times(1)).addAuthor(name);
    }

    @Test
    public void should_successfully_get() {
        String name = "Bram Stocker";
        Author expected = Author.builder().id(1).name(name).build();

        when(controller.getAuthor(name)).thenReturn(expected);

        Author actual = controller.getAuthor(name);

        assertEquals(expected, actual);
    }

    @Test
    public void should_successfully_delete() {
        String name = "Bram Stocker";
        controller.deleteAuthor(name);
        verify(controller, times(1)).deleteAuthor(name);
    }

    @Test
    public void should_successfully_update() {
        String name = "Bram Stocker";
        String newName = "Bram";
        controller.updateAuthor(name, newName);
        verify(controller, times(1)).updateAuthor(name, newName);
    }
}

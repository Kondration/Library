package serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.example.entity.Author;
import org.example.service.AuthorService;
import org.junit.jupiter.api.Test;

public class AuthorServiceTest {
    private AuthorService service = mock(AuthorService.class);

    @Test
    public void should_successfully_add() {
        String name = "Bram Stocker";
        service.add(name);
        verify(service, times(1)).add(name);
    }

    @Test
    public void should_successfully_get() {
        String name = "Bram Stocker";
        Author expected = Author.builder().id(1).name(name).build();

        when(service.get(name)).thenReturn(expected);

        Author actual = service.get(name);

        assertEquals(expected, actual);
    }

    @Test
    public void should_successfully_delete() {
        String name = "Bram Stocker";
        service.delete(name);
        verify(service, times(1)).delete(name);
    }
}

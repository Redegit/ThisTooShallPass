import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.server.api.UserController;
import org.server.entity.User;
import org.server.repository.UserRepository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {


    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserController controller;

    @Test
    @DisplayName("GET /api/users возвращает HTTP-ответ со статусом 200 OK и списком пользователей")
    void handleGetAllUsers_ReturnsValidResponseEntity() {
        // given
        var user = new User();
        user.setName("DmitryP");
        user.setPassword("123");
        var users = List.of(
                user
        );
        doReturn(users).when(this.userRepository).findAll();

        // when
        var responseEntity = this.controller.getAllUsers();

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(users, responseEntity.getBody());
    }


    @Test
    @DisplayName("GET /api/users возвращает HTTP-ответ со статусом 200 OK и списком пользователей")
    void handleGetUserByID_ReturnsValidResponseEntity() {
        // given
        var user = new User();
        user.setName("DmitryP");
//        user.setPassword("$2a$10$9Cb10Lwl9bQ9X2tO1o8fl.T0DU0Uj0TTvlIY6opirZ5CLIzUJH5Xi");

        doReturn(Optional.of(user)).when(this.userRepository).findById(1);

        // when
        var responseEntity = this.controller.getUserById(1);
        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(user, responseEntity.getBody());
    }

}

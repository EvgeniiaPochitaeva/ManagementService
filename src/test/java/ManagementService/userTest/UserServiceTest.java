package ManagementService.userTest;
import ManagementService.user.User;
import ManagementService.user.UserRepository;
import ManagementService.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindByUsername_UserExists() {
        User mockUser = User.builder()
                .userId("test_user")
                .passwordHash("test_password")
                .build();
        Optional<User> optionalUser = Optional.of(mockUser);

        when(userRepository.findById("test_user")).thenReturn(optionalUser);

        User result = userService.findByUsername("test_user");

        assertEquals(mockUser, result);
    }

    @Test
    public void testSaveUser() {
        User mockUser = User.builder()
                .userId("test_user")
                .passwordHash("test_password")
                .build();

        userService.saveUser(mockUser);

        verify(userRepository, times(1)).save(mockUser);
    }
}


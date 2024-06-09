package ManagementService.auth;

import ManagementService.auth.dto.login.LoginRequest;
import ManagementService.auth.dto.login.LoginResponse;
import ManagementService.auth.dto.registration.RegistrationRequest;
import ManagementService.auth.dto.registration.RegistrationResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
        @PostMapping("/register")
    public RegistrationResponse register(@Valid @RequestBody RegistrationRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
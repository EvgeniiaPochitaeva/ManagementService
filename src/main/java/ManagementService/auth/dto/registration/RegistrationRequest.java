package ManagementService.auth.dto.registration;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String email;
    private String password;

}

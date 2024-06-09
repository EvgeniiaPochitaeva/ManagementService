package ManagementService.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

}
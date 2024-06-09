package ManagementService.company;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "Company")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt = Timestamp.from(Instant.now());


}

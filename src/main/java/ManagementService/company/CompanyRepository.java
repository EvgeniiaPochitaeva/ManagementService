package ManagementService.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EnableJpaRepositories
public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
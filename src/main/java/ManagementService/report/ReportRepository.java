package ManagementService.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.UUID;

@EnableJpaRepositories
public interface ReportRepository extends JpaRepository<Report, UUID> {
    List<Report> findByCompanyId(UUID companyId);
}

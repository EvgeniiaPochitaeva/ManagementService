package ManagementService.reportDetails;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.UUID;

@EnableMongoRepositories
public interface ReportDetailsRepository extends MongoRepository<ReportDetails, UUID> {
}
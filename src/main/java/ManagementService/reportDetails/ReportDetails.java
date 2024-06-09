package ManagementService.reportDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "report_details")
public class ReportDetails {

    @Id
    private String id;

    private UUID reportId;

    private Map<String, Object> financialData;

    private String comments;
}

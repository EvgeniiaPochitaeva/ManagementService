package ManagementService.reportDetails;

import ManagementService.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/reportDetails")
public class ReportDetailsController {

    @Autowired
    private ReportDetailsService reportDetailsService;

    @GetMapping("/{reportId}")
    public ResponseEntity<ReportDetails> getReportDetailsById(@PathVariable UUID reportId) {
        Optional<ReportDetails> reportDetails = reportDetailsService.getReportDetailsById(reportId);
        return reportDetails.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("ReportDetails not found for id: " + reportId));
    }

    @PostMapping
    public ResponseEntity<ReportDetails> createReportDetails(@RequestBody ReportDetails reportDetails) {
        ReportDetails createdReportDetails = reportDetailsService.createReportDetails(reportDetails);
        return ResponseEntity.ok(createdReportDetails);
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<ReportDetails> updateReportDetails(@PathVariable UUID reportId, @RequestBody ReportDetails reportDetailsData) {
        ReportDetails updatedReportDetails = reportDetailsService.updateReportDetails(reportId, reportDetailsData);
        return ResponseEntity.ok(updatedReportDetails);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> deleteReportDetails(@PathVariable UUID reportId) {
        reportDetailsService.deleteReportDetails(reportId);
        return ResponseEntity.noContent().build();
    }
}

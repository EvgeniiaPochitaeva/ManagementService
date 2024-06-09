package ManagementService.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Create a new report
    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report createdReport = reportService.createReport(report);
        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }

    // Receive all reports
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    // Receiving a report by its ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getReportById(@PathVariable UUID id) {
        Optional<Report> optionalReport = reportService.getReportById(id);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            return new ResponseEntity<>(report, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Report not found", HttpStatus.NOT_FOUND);
        }
    }

    // Receiving all reports for a specific company by its ID
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Report>> getReportsByCompanyId(@PathVariable UUID companyId) {
        List<Report> reports = reportService.getReportsByCompanyId(companyId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    // Updating a report by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable UUID id, @RequestBody Report reportData) {
        Report updatedReport = reportService.updateReport(id, reportData);
        return new ResponseEntity<>(updatedReport, HttpStatus.OK);
    }

    // Deleting a report by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable UUID id) {
        reportService.deleteReport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

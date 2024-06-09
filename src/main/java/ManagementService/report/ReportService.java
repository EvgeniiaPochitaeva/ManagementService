package ManagementService.report;

import ManagementService.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public List<Report> getReportsByCompanyId(UUID companyId) {
        return reportRepository.findByCompanyId(companyId);
    }

    public Optional<Report> getReportById(UUID id) {
        return reportRepository.findById(id);
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public Report updateReport(UUID id, Report reportDetails) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report not found"));
        report.setReportDate(reportDetails.getReportDate());
        report.setTotalRevenue(reportDetails.getTotalRevenue());
        report.setNetProfit(reportDetails.getNetProfit());
        return reportRepository.save(report);
    }

    public void deleteReport(UUID id) {
        reportRepository.deleteById(id);
    }
}
package ManagementService.reportDetails;

import ManagementService.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReportDetailsService {
    @Autowired
    private ReportDetailsRepository reportDetailsRepository;

    public Optional<ReportDetails> getReportDetailsById(UUID reportId) {
        return reportDetailsRepository.findById(reportId);
    }

    public ReportDetails createReportDetails(ReportDetails reportDetails) {
        return reportDetailsRepository.save(reportDetails);
    }

    public ReportDetails updateReportDetails(UUID reportId, ReportDetails reportDetailsData) {
        ReportDetails reportDetails = reportDetailsRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("ReportDetails not found"));
        reportDetails.setFinancialData(reportDetailsData.getFinancialData());
        reportDetails.setComments(reportDetailsData.getComments());
        return reportDetailsRepository.save(reportDetails);
    }

    public void deleteReportDetails(UUID reportId) {
        reportDetailsRepository.deleteById(reportId);
    }
}
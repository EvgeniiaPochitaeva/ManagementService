package ManagementService.reportTest;

import ManagementService.report.Report;
import ManagementService.report.ReportRepository;
import ManagementService.report.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateReport() {
        // Arrange
        Report testReport = createTestReport();

        when(reportRepository.save(testReport)).thenReturn(testReport);

        // Act
        Report createdReport = reportService.createReport(testReport);

        // Assert
        assertEquals(testReport.getId(), createdReport.getId());
        assertEquals(testReport.getReportDate(), createdReport.getReportDate());
        assertEquals(testReport.getTotalRevenue(), createdReport.getTotalRevenue());
        assertEquals(testReport.getNetProfit(), createdReport.getNetProfit());

        // Verify
        verify(reportRepository, times(1)).save(testReport);
    }

    private Report createTestReport() {
        UUID expectedId = UUID.fromString("5283d6a4-8f41-421b-8e03-e7ff72a2e593");
        return Report.builder()
                .id(expectedId)
                .reportDate(Timestamp.from(Instant.now()))
                .totalRevenue(new BigDecimal("500.00"))
                .netProfit(new BigDecimal("250.00"))
                .build();
    }
}

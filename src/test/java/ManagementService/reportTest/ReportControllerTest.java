package ManagementService.reportTest;

import ManagementService.company.Company;
import ManagementService.report.Report;
import ManagementService.report.ReportController;
import ManagementService.report.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @Test
    public void testCreateReport() {
        Company company = Company.builder().name("Test Company").build();

        Report mockReport = Report.builder()
                .id(UUID.randomUUID())
                .company(company)
                .reportDate(Timestamp.from(Instant.now()))
                .totalRevenue(BigDecimal.valueOf(1000))
                .netProfit(BigDecimal.valueOf(500))
                .build();

        when(reportService.createReport(any(Report.class))).thenReturn(mockReport);

        ResponseEntity<Report> response = reportController.createReport(mockReport);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockReport, response.getBody());
    }

    @Test
    public void testGetAllReports() {
        List<Report> mockReports = Arrays.asList(
                Report.builder().id(UUID.randomUUID()).company(new Company()).reportDate(Timestamp.from(Instant.now())).totalRevenue(BigDecimal.valueOf(1000)).netProfit(BigDecimal.valueOf(500)).build(),
                Report.builder().id(UUID.randomUUID()).company(new Company()).reportDate(Timestamp.from(Instant.now())).totalRevenue(BigDecimal.valueOf(2000)).netProfit(BigDecimal.valueOf(1000)).build()
        );

        when(reportService.getAllReports()).thenReturn(mockReports);

        ResponseEntity<List<Report>> response = reportController.getAllReports();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReports, response.getBody());
    }

    @Test
    public void testGetReportById_ReportExists() {
        UUID reportId = UUID.randomUUID();
        Report mockReport = Report.builder().id(reportId).company(new Company()).reportDate(Timestamp.from(Instant.now())).totalRevenue(BigDecimal.valueOf(1000)).netProfit(BigDecimal.valueOf(500)).build();

        when(reportService.getReportById(reportId)).thenReturn(Optional.of(mockReport));

        ResponseEntity<?> response = reportController.getReportById(reportId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReport, response.getBody());
    }

    @Test
    public void testGetReportById_ReportNotExists() {
        UUID reportId = UUID.randomUUID();

        when(reportService.getReportById(reportId)).thenReturn(Optional.empty());

        ResponseEntity<?> response = reportController.getReportById(reportId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Report not found", response.getBody());
    }

    @Test
    public void testGetReportsByCompanyId() {
        UUID companyId = UUID.randomUUID();
        List<Report> mockReports = Arrays.asList(
                Report.builder().id(UUID.randomUUID()).company(new Company()).reportDate(Timestamp.from(Instant.now())).totalRevenue(BigDecimal.valueOf(1000)).netProfit(BigDecimal.valueOf(500)).build(),
                Report.builder().id(UUID.randomUUID()).company(new Company()).reportDate(Timestamp.from(Instant.now())).totalRevenue(BigDecimal.valueOf(2000)).netProfit(BigDecimal.valueOf(1000)).build()
        );

        when(reportService.getReportsByCompanyId(companyId)).thenReturn(mockReports);

        ResponseEntity<List<Report>> response = reportController.getReportsByCompanyId(companyId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReports, response.getBody());
    }

    @Test
    public void testUpdateReport() {
        UUID reportId = UUID.randomUUID();
        Report updatedReport = Report.builder().id(reportId).company(new Company()).reportDate(Timestamp.from(Instant.now())).totalRevenue(BigDecimal.valueOf(1500)).netProfit(BigDecimal.valueOf(750)).build();

        when(reportService.updateReport(eq(reportId), any(Report.class))).thenReturn(updatedReport);

        ResponseEntity<Report> response = reportController.updateReport(reportId, updatedReport);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedReport, response.getBody());
    }

    @Test
    public void testDeleteReport() {
        UUID reportId = UUID.randomUUID();

        ResponseEntity<Void> response = reportController.deleteReport(reportId);

        verify(reportService, times(1)).deleteReport(reportId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

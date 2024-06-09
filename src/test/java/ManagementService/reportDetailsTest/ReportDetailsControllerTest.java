package ManagementService.reportDetailsTest;

import ManagementService.exceptions.ResourceNotFoundException;
import ManagementService.reportDetails.ReportDetails;
import ManagementService.reportDetails.ReportDetailsController;
import ManagementService.reportDetails.ReportDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportDetailsControllerTest {

    @Mock
    private ReportDetailsService reportDetailsService;

    @InjectMocks
    private ReportDetailsController reportDetailsController;

    @Test
    public void testGetReportDetailsById_ReportDetailsExists() {
        UUID reportId = UUID.randomUUID();
        ReportDetails mockReportDetails = createMockReportDetails(reportId);

        when(reportDetailsService.getReportDetailsById(reportId)).thenReturn(Optional.of(mockReportDetails));

        ResponseEntity<ReportDetails> response = reportDetailsController.getReportDetailsById(reportId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReportDetails, response.getBody());
    }

    @Test
    public void testGetReportDetailsById_ReportDetailsNotExists() {
        UUID reportId = UUID.randomUUID();

        when(reportDetailsService.getReportDetailsById(reportId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> reportDetailsController.getReportDetailsById(reportId));
        assertEquals("ReportDetails not found for id: " + reportId, exception.getMessage());
    }

    @Test
    public void testCreateReportDetails() {
        ReportDetails mockReportDetails = createMockReportDetails();
        when(reportDetailsService.createReportDetails(any(ReportDetails.class))).thenReturn(mockReportDetails);

        ResponseEntity<ReportDetails> response = reportDetailsController.createReportDetails(mockReportDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReportDetails, response.getBody());
    }

    @Test
    public void testUpdateReportDetails_ReportDetailsExists() {
        UUID reportId = UUID.randomUUID();
        ReportDetails mockReportDetails = createMockReportDetails(reportId);
        ReportDetails updatedReportDetails = createMockReportDetails(reportId);

        when(reportDetailsService.updateReportDetails(eq(reportId), any(ReportDetails.class))).thenReturn(updatedReportDetails);

        ResponseEntity<ReportDetails> response = reportDetailsController.updateReportDetails(reportId, updatedReportDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedReportDetails, response.getBody());
    }

    @Test
    public void testDeleteReportDetails() {
        UUID reportId = UUID.randomUUID();

        ResponseEntity<Void> response = reportDetailsController.deleteReportDetails(reportId);

        verify(reportDetailsService, times(1)).deleteReportDetails(reportId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private ReportDetails createMockReportDetails(UUID reportId) {
        return ReportDetails.builder()
                .id(UUID.randomUUID().toString())
                .reportId(reportId)
                .financialData(Map.of("key", "value"))
                .comments("Some comments")
                .build();
    }

    private ReportDetails createMockReportDetails() {
        return createMockReportDetails(UUID.randomUUID());
    }
}

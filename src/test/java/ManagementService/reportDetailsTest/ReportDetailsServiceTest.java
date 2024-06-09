package ManagementService.reportDetailsTest;

import ManagementService.exceptions.ResourceNotFoundException;
import ManagementService.reportDetails.ReportDetails;
import ManagementService.reportDetails.ReportDetailsService;
import ManagementService.reportDetails.ReportDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportDetailsServiceTest {

    @Mock
    private ReportDetailsRepository reportDetailsRepository;

    @InjectMocks
    private ReportDetailsService reportDetailsService;

    @Test
    public void testGetReportDetailsById_ReportDetailsExists() {
        UUID reportId = UUID.randomUUID();
        ReportDetails mockReportDetails = createMockReportDetails(reportId);

        when(reportDetailsRepository.findById(reportId)).thenReturn(Optional.of(mockReportDetails));

        Optional<ReportDetails> result = reportDetailsService.getReportDetailsById(reportId);

        assertTrue(result.isPresent());
        assertEquals(mockReportDetails, result.get());
    }

    @Test
    public void testGetReportDetailsById_ReportDetailsNotExists() {
        UUID reportId = UUID.randomUUID();

        when(reportDetailsRepository.findById(reportId)).thenReturn(Optional.empty());

        Optional<ReportDetails> result = reportDetailsService.getReportDetailsById(reportId);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testCreateReportDetails() {
        ReportDetails mockReportDetails = createMockReportDetails(null);
        when(reportDetailsRepository.save(any(ReportDetails.class))).thenReturn(mockReportDetails);

        ReportDetails result = reportDetailsService.createReportDetails(mockReportDetails);

        assertNotNull(result);
        assertEquals(mockReportDetails, result);
    }

    @Test
    public void testUpdateReportDetails_ReportDetailsExists() {
        UUID reportId = UUID.randomUUID();
        ReportDetails mockReportDetails = createMockReportDetails(reportId);
        ReportDetails updatedReportDetails = createMockReportDetails(reportId);

        when(reportDetailsRepository.findById(reportId)).thenReturn(Optional.of(mockReportDetails));
        when(reportDetailsRepository.save(any(ReportDetails.class))).thenReturn(updatedReportDetails);

        ReportDetails result = reportDetailsService.updateReportDetails(reportId, updatedReportDetails);

        assertNotNull(result);
        assertEquals(updatedReportDetails, result);
    }

    @Test
    public void testUpdateReportDetails_ReportDetailsNotExists() {
        UUID reportId = UUID.randomUUID();
        ReportDetails updatedReportDetails = createMockReportDetails(reportId);

        when(reportDetailsRepository.findById(reportId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reportDetailsService.updateReportDetails(reportId, updatedReportDetails));
    }

    @Test
    public void testDeleteReportDetails() {
        UUID reportId = UUID.randomUUID();

        assertDoesNotThrow(() -> reportDetailsService.deleteReportDetails(reportId));
        verify(reportDetailsRepository, times(1)).deleteById(reportId);
    }

    private ReportDetails createMockReportDetails(UUID reportId) {
        return ReportDetails.builder()
                .id(UUID.randomUUID().toString())
                .reportId(reportId)
                .financialData(null) // Provide appropriate financial data here
                .comments("Some comments")
                .build();
    }
}

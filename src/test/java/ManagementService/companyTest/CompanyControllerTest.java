package ManagementService.companyTest;

import ManagementService.company.Company;
import ManagementService.company.CompanyController;
import ManagementService.company.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @Test
    public void testGetAllCompanies() {
        List<Company> mockCompanies = Arrays.asList(
                Company.builder()
                        .id(UUID.randomUUID())
                        .name("Company A")
                        .registrationNumber("1234567890")
                        .address("123 Main St")
                        .createdAt(Timestamp.from(Instant.now()))
                        .build(),
                Company.builder()
                        .id(UUID.randomUUID())
                        .name("Company B")
                        .registrationNumber("0987654321")
                        .address("456 Elm St")
                        .createdAt(Timestamp.from(Instant.now()))
                        .build()
        );

        when(companyService.getAllCompanies()).thenReturn(mockCompanies);

        List<Company> result = companyController.getAllCompanies();

        assertEquals(mockCompanies, result);
    }

    @Test
    public void testGetCompanyById_CompanyExists() {
        UUID companyId = UUID.randomUUID();
        Company mockCompany = Company.builder()
                .id(companyId)
                .name("Test Company")
                .registrationNumber("1234567890")
                .address("789 Oak St")
                .createdAt(Timestamp.from(Instant.now()))
                .build();
        ResponseEntity<Company> expectedResponse = ResponseEntity.ok(mockCompany);

        when(companyService.getCompanyById(companyId)).thenReturn(Optional.of(mockCompany));

        ResponseEntity<Company> result = companyController.getCompanyById(companyId);

        assertEquals(expectedResponse, result);
    }

    @Test
    public void testGetCompanyById_CompanyNotExists() {
        UUID companyId = UUID.randomUUID();

        when(companyService.getCompanyById(companyId)).thenReturn(Optional.empty());

        ResponseEntity<Company> result = companyController.getCompanyById(companyId);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testCreateCompany() {
        Company mockCompany = Company.builder()
                .id(UUID.randomUUID())
                .name("Test Company")
                .registrationNumber("1234567890")
                .address("789 Oak St")
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        when(companyService.createCompany(any(Company.class))).thenReturn(mockCompany);

        Company result = companyController.createCompany(mockCompany);

        assertEquals(mockCompany, result);
    }

    @Test
    public void testUpdateCompany() {
        UUID companyId = UUID.randomUUID();
        Company mockCompany = Company.builder()
                .id(companyId)
                .name("Test Company")
                .registrationNumber("1234567890")
                .address("789 Oak St")
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        when(companyService.updateCompany(eq(companyId), any(Company.class))).thenReturn(mockCompany);

        ResponseEntity<Company> result = companyController.updateCompany(companyId, mockCompany);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockCompany, result.getBody());
    }

    @Test
    public void testDeleteCompany() {
        UUID companyId = UUID.randomUUID();

        ResponseEntity<Void> result = companyController.deleteCompany(companyId);

        verify(companyService, times(1)).deleteCompany(companyId);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}

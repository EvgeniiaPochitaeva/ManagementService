package ManagementService.companyTest;

import ManagementService.company.CompanyRepository;
import ManagementService.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ManagementService.company.Company;
import ManagementService.company.CompanyService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    public void testGetAllCompanies() {
        List<Company> mockCompanies = Arrays.asList(
                new Company(UUID.randomUUID(), "Company A", "1234567890", "123 Main St", Timestamp.from(Instant.now())),
                new Company(UUID.randomUUID(), "Company B", "0987654321", "456 Elm St", Timestamp.from(Instant.now()))
        );

        when(companyRepository.findAll()).thenReturn(mockCompanies);

        List<Company> result = companyService.getAllCompanies();

        assertEquals(mockCompanies, result);
    }

    @Test
    public void testGetCompanyById_CompanyExists() {
        UUID companyId = UUID.randomUUID();
        Company mockCompany = new Company(companyId, "Test Company", "1234567890", "789 Oak St", Timestamp.from(Instant.now()));

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(mockCompany));

        Optional<Company> result = companyService.getCompanyById(companyId);

        assertTrue(result.isPresent());
        assertEquals(mockCompany, result.get());
    }

    @Test
    public void testGetCompanyById_CompanyNotExists() {
        UUID companyId = UUID.randomUUID();

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> companyService.getCompanyById(companyId));
    }

    @Test
    public void testCreateCompany() {
        Company mockCompany = new Company(UUID.randomUUID(), "Test Company", "1234567890", "789 Oak St", Timestamp.from(Instant.now()));

        when(companyRepository.save(any(Company.class))).thenReturn(mockCompany);

        Company result = companyService.createCompany(mockCompany);

        assertEquals(mockCompany, result);
    }

    @Test
    public void testUpdateCompany_CompanyExists() {
        UUID companyId = UUID.randomUUID();
        Company existingCompany = new Company(companyId, "Existing Company", "1234567890", "789 Oak St", Timestamp.from(Instant.now()));
        Company updatedCompany = new Company(companyId, "Updated Company", "0987654321", "456 Elm St", Timestamp.from(Instant.now()));

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(existingCompany));
        when(companyRepository.save(any(Company.class))).thenReturn(updatedCompany);

        Company result = companyService.updateCompany(companyId, updatedCompany);

        assertEquals(updatedCompany, result);
    }

    @Test
    public void testUpdateCompany_CompanyNotExists() {
        UUID companyId = UUID.randomUUID();
        Company updatedCompany = new Company(companyId, "Updated Company", "0987654321", "456 Elm St", Timestamp.from(Instant.now()));

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> companyService.updateCompany(companyId, updatedCompany));
    }

    @Test
    public void testDeleteCompany() {
        UUID companyId = UUID.randomUUID();

        companyService.deleteCompany(companyId);

        verify(companyRepository, times(1)).deleteById(companyId);
    }
}

package ManagementService.company;

import ManagementService.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(UUID id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company;
        } else {
            throw new ResourceNotFoundException("Company not found with id: " + id);
        }
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(UUID id, Company companyDetails) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        company.setName(companyDetails.getName());
        company.setRegistrationNumber(companyDetails.getRegistrationNumber());
        company.setAddress(companyDetails.getAddress());
        company.setCreatedAt(companyDetails.getCreatedAt());
        return companyRepository.save(company);
    }

    public void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
    }
}
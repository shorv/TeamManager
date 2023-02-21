package io.github.shorv.teammanager.organization;

import io.github.shorv.teammanager.organization.exception.OrganizationNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    private List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    private List<Organization> getPaginatedOrganizations(int page, int size) {
        return organizationRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    private List<Organization> getSortedOrganizations(String sortDirection, String field) {
        return organizationRepository.findAll(Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), field));
    }

    private List<Organization> getSortedAndPaginatedOrganizations(int page, int size, String sortDirection, String field) {
        return organizationRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), field))).getContent();
    }

    public List<Organization> getOrganizations(Integer page, Integer size, String sortDirection, String sortField) {
        boolean pageable = !(page == null || size == null);
        boolean sortable = !(sortDirection == null || sortField == null);

        if (!pageable) {
            if (!sortable) {
                return getAllOrganizations();
            }

            return getSortedOrganizations(sortDirection, sortField);
        }

        if (!sortable) {
            return getPaginatedOrganizations(page, size);
        }

        return getSortedAndPaginatedOrganizations(page, size, sortDirection, sortField);
    }

    public Organization getOrganizationById(Long organizationId) {
        return organizationRepository.findById(organizationId)
                .orElseThrow(OrganizationNotFoundException::new);
    }

    public void addNewOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    public void deleteOrganizationById(Long organizationId) {
        organizationRepository.deleteById(organizationId);
    }
}

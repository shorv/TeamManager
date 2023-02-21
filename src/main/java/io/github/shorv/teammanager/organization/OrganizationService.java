package io.github.shorv.teammanager.organization;

import io.github.shorv.teammanager.PageableAndSortableService;
import io.github.shorv.teammanager.organization.exception.OrganizationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService extends PageableAndSortableService<Organization, OrganizationRepository> {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository repository, OrganizationRepository organizationRepository) {
        super(repository);
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<Organization> getAll(Integer page, Integer size, String sortDirection, String sortField) {
        return super.getAll(page, size, sortDirection, sortField);
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

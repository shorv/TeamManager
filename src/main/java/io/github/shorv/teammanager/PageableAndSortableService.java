package io.github.shorv.teammanager;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@AllArgsConstructor
public abstract class PageableAndSortableService<T, R extends JpaRepository<T, ?>> {

    private final R repository;

    private List<T> getPaginated(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    private List<T> getSorted(String sortDirection, String field) {
        return repository.findAll(Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), field));
    }

    private List<T> getSortedAndPaginated(int page, int size, String sortDirection, String field) {
        return repository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), field))).getContent();
    }

    public List<T> getAll(Integer page, Integer size, String sortDirection, String sortField) {
        boolean pageable = !(page == null || size == null);
        boolean sortable = !(sortDirection == null || sortField == null);

        if (!pageable) {
            if (!sortable) {
                return repository.findAll();
            }

            return getSorted(sortDirection, sortField);
        }

        if (!sortable) {
            return getPaginated(page, size);
        }

        return getSortedAndPaginated(page, size, sortDirection, sortField);
    }
}

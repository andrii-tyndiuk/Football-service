package spring.projects.footballmanager.service.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}

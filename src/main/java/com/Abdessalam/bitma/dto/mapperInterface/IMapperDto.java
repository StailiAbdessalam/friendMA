package com.Abdessalam.friendMA.dto.mapperInterface;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface IMapperDto<E,D> {

    D convertToDto(E entity, Class<D> dtoClass);

    E convertToEntity(D dto, Class<E> entityClass);

    List<D> convertListToListDto(Collection<E> entityList, Class<D> outCLass);

    Page<D> convertPageToPageDto(Page<E> entityList, Class<D> outClass);

    List<E> convertListToListEntity(Collection<D> DtoList, Class<E> entityCLass);
}

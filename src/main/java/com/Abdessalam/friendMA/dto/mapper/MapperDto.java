package com.Abdessalam.friendMA.dto.mapper;

import com.Abdessalam.friendMA.dto.mapperInterface.IMapperDto;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperDto <E, D> implements IMapperDto<E, D> {


    private final ModelMapper modelMapper;

    public MapperDto(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public D convertToDto(E entity, Class<D> dtoClass) {
        if(entity == null){
            return null;
        }
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return modelMapper.map(entity,dtoClass);
    }

    @Override
    public E convertToEntity(D dto, Class<E> entityClass) {
        if(dto == null)
            return null;

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public List<D> convertListToListDto(Collection<E> entityList, Class<D> outCLass) {
        if(entityList == null)
            return Collections.emptyList();

        return entityList.stream().map(entity -> convertToDto(entity, outCLass)).collect(Collectors.toList());
    }

    @Override
    public Page<D> convertPageToPageDto(Page<E> entityList, Class<D> outClass) {
        if(entityList == null)
            return Page.empty();

        List<D> all =  entityList.stream().map(entity -> convertToDto(entity,outClass)).collect(Collectors.toList());
        return new PageImpl<>(all);
    }

    @Override
    public List<E> convertListToListEntity(Collection<D> DtoList, Class<E> entityCLass) {
        if(DtoList == null)
            return Collections.emptyList();

        return DtoList.stream().map(dto -> convertToEntity(dto,entityCLass)).collect(Collectors.toList());
    }
}

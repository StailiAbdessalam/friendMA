package com.Abdessalam.friendMA.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapString<S,E> {

    private final ModelMapper modelMapper;

    public E convertToEntity(S mapString, Class<E> entityClass) {
        if(mapString == null)
            return null;

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return modelMapper.map(mapString, entityClass);
    }
}

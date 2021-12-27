package com.cot.app.backend.db.dao.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

/**
 * @author davidjmartin
 */
@Slf4j
@Component
public class ReportMapper {

    @Autowired
    private ModelMapper modelMapper;

    private ReportMapper() {}

    public <S, T> T toDto(S source, Class<T> targetClass) {
        log.debug("mapping report entity: {} to dto.", source);
        return modelMapper.map(source, targetClass);
    }

    public <S, T> List<T> toList(List<S> source, Class<T> targetClass) {
        log.debug("mapping report entities to dtos.");
        return source
            .stream()
            .map(element -> modelMapper.map(element, targetClass))
            .collect(Collectors.toList());
    }

}

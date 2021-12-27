package cot.swap.db;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author davidjmartin
 */
@Slf4j
@Component
public class EntityMapper {

    @Autowired
    private ModelMapper modelMapper;

    private EntityMapper() {
    }

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


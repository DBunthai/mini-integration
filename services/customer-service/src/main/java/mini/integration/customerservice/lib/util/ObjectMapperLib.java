package mini.integration.customerservice.lib.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapperLib {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static ObjectMapper objectMapper() {
        return objectMapper;
    }

    public static <T, E> List<E> mapList(List<T> list, Class<E> map) {
        return list.stream()
            .map(m -> objectMapper.convertValue(m, map))
            .collect(Collectors.toList());
    }

    public static <T, E> E mapObj(T obj, Class<E> map) {
        return objectMapper.convertValue(obj, map);
    }
}

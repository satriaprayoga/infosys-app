package com.infosys.customer.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.util.StringUtils;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 923501814328378550L;
	
	public ResourceNotFoundException(Class<?> clazz, String... searchParamsMap) {
        super(generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " was not found for parameters " +
                searchParams;
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}

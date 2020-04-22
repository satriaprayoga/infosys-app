package com.infosys.data.dto.errors;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.util.StringUtils;

public class ResourceConstraintException extends RuntimeException {

	
	private static final long serialVersionUID = -2571913541893966353L;
	

	public ResourceConstraintException(Class<?> clazz, String... searchParamsMap) {
		super(generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
	}
	
	  private static String generateMessage(String entity, Map<String, String> searchParams) {
	        return StringUtils.capitalize(entity) +
	                " has childs " +
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

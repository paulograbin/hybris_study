package com.paulograbin.core.cleanup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by scaggiani on 26/01/18.
 */
public class MappingRegistry<K, V>
{

	private final Map<K, V> mappings = new HashMap<>();

	public void addMapping(K key, V value)
	{
		mappings.put(key, value);
	}

	public Map<K, V> getMappings()
	{
		return Collections.unmodifiableMap(mappings);
	}
}

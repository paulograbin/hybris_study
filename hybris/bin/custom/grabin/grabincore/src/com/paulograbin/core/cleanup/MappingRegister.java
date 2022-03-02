package com.paulograbin.core.cleanup;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by scaggiani on 26/01/18.
 */
public class MappingRegister<K, V>
{

	private final MappingRegistry<K, V> registry;

	private K key;
	private V value;

	@Autowired
	public MappingRegister(MappingRegistry<K, V> registry)
	{
		this.registry = registry;
	}

	public void setKey(K key)
	{
		this.key = key;
	}

	public void setValue(V value)
	{
		this.value = value;
	}

	@PostConstruct
	public void registerMapping()
	{
		registry.addMapping(key, value);
	}
}

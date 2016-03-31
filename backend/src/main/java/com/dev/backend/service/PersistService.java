package com.dev.backend.service;

import java.util.List;

public interface PersistService <E> {
	boolean save(E customer);
	E get(String code);
	boolean delete(String code);
	List<E> getAll();
	void deleteAll();
}

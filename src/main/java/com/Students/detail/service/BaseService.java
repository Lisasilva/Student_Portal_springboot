package com.Students.detail.service;

import java.util.List;

public interface BaseService<T> {
	 List<T> getAll();
	 T add(T data);
	 T edit(T data);
	 String delete(Long id);
	 T getById(Long id);
	 
}

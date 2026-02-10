package com.bharatgas.crm.service;

import java.util.List;

/**
 * Generic CRUD operations interface.
 * Demonstrates: Abstraction through interfaces, Generics.
 *
 * @param <T> the entity type
 */
public interface CRUDOperations<T> {
    void add(T item);

    void update(String id, T item);

    void delete(String id);

    T getById(String id);

    List<T> getAll();
}

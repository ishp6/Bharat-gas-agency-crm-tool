package com.bharatgas.crm.service;

import java.util.List;

/**
 * Interface for searchable entities.
 * Demonstrates: Abstraction, Interface segregation.
 *
 * @param <T> the entity type
 */
public interface Searchable<T> {
    List<T> searchByName(String name);
}

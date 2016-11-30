package ru.tersoft.service;

import ru.tersoft.entity.Category;

import java.util.UUID;

public interface CategoryService {
    Iterable<Category> getAll();
    Category get(UUID id);
    Category add(Category category);
    Boolean delete(UUID id);
    Boolean edit(Category category);
}

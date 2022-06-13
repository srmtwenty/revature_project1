package com.revature.repository;

import java.util.List;

public interface DAO<T> {

    T create(T t);

    List<T> getAll();

    T getById(int id);

    T update(T t);

    boolean deleteById(int id);
}

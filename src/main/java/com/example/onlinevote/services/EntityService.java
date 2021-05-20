package com.example.onlinevote.services;

public interface EntityService<V> {
    V save(V entity);

    void update(V entity);

    void remove(V entity);
}

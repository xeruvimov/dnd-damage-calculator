package com.dnd.json;

public interface Mapper<T> {
    String toJson(T entity);
    T toObject(String json);
}

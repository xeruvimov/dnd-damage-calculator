package com.dnd.json.impl;

import com.dnd.domain.DataModel;
import com.dnd.json.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperImpl implements Mapper<DataModel> {
    @Override
    public String toJson(DataModel entity) {
        return new Gson().toJson(entity);
    }

    @Override
    public DataModel toObject(String json) {
        return new Gson().fromJson(json, DataModel.class);
    }
}

package com.uss.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Serializer;

/**
 * Created by ulisses on 22/05/2023.
 */
public class JsonSerializer<T> implements Serializer<T> {

    private final Gson gson = new GsonBuilder().create();


    public byte[] serialize(String var1, T var2){
        return gson.toJson(var2).getBytes();
    }



}

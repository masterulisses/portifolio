package com.uss.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * Created by ulisses on 22/05/2023.
 */
public class JsonProdutoDeserializer<T> implements Deserializer<T> {


    private final Gson gson = new GsonBuilder().setDateFormat("yyyy,MM,dd,HH,mm,ss,zzz").create();
    private Class<T> type;

    public void configure(Map<String, ?> configs, boolean isKey) {
        try{
            this.type = (Class<T>) Class.forName("com.uss.to.produto.ProdutoTO");
        }catch (Exception e){
            throw new RuntimeException("Classe não existe. Erro:" +  e.getMessage());
        }

    }


    public T deserialize(String var1, byte[] bytes){
        return gson.fromJson(new String(bytes), type);
    }



}

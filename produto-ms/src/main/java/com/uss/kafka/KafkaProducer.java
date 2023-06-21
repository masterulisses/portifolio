package com.uss.kafka;

import com.uss.to.ProdutoTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ulisses on 23/05/2023.
 */
@Component
public class KafkaProducer {


    private final KafkaTemplate<String, ProdutoTO> kafkaTemplate;
    @Value("${kafka.topic.produto}")
    private String topic;


    public KafkaProducer(KafkaTemplate<String, ProdutoTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ProdutoTO produtoTO) {
        CompletableFuture.supplyAsync(() -> {
            kafkaTemplate.send(this.topic, produtoTO.getId().toString(), produtoTO);
            return "Mensagem enviada: " + produtoTO;
        });
    }
}

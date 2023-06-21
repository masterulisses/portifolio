package com.uss.kafka;

import com.uss.service.produto.ProdutoService;
import com.uss.to.produto.ProdutoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by ulisses on 24/05/2023.
 */
@Component
public class KafkaConsumerProduto {

    @Autowired
    private ProdutoService produtoService;

    /*@KafkaListener(topics = "${kafka.topic.produto}", groupId = "KafkaConsumerProduto")
    public void consumer(final ConsumerRecord consumerRecord) {
        System.out.println("key: " + consumerRecord.key());
        System.out.println("Headers: " + consumerRecord.headers());
        System.out.println("Partion: " + consumerRecord.partition());
        System.out.println("Produto: " + consumerRecord.value());
    }*/

    @KafkaListener(topics = "${kafka.topic.produto}", groupId = "kafkaConsumerProduto", properties = {"value.deserializer=com.uss.kafka.JsonProdutoDeserializer"})
    public void consumer(final ProdutoTO produtoTO) {
        System.out.println("INICIO PRODUTO RECEBIDO DO KAFKA: " + produtoTO);
        this.produtoService.incluirAlterar(produtoTO);
        System.out.println("FIM PRODUTO RECEBIDO DO KAFKA: " + produtoTO);
    }
}

package com.example.consumer2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.consumer2.KafkaConsumerConfig.GROUP_ID_PRODUTO_CONSUMER;
import static com.example.consumer2.KafkaConsumerConfig.SEU_TOPICO_PRODUTO;

@Slf4j
@Component
public class ProdutoConsumer {
    @KafkaListener(topics = SEU_TOPICO_PRODUTO, groupId = GROUP_ID_PRODUTO_CONSUMER)
    public void listen(Produto produto) {
        log.info("Produto recebido: " + produto.getNome());
    }
}

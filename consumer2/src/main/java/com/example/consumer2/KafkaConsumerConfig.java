package com.example.consumer2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    public static final String SEU_TOPICO_PRODUTO = "SEU_TOPICO_PRODUTO";
    public static final String GROUP_ID_PRODUTO_CONSUMER = "GROUP_PRODUTO_CONSUMER2";

    @Bean
    public ConsumerFactory<String, Produto> produtoConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.18.57:9092,192.168.18.57:9094,192.168.18.57:9096");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_PRODUTO_CONSUMER);
        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                new JsonDeserializer<>(Produto.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Produto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Produto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(produtoConsumerFactory());
        return factory;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}

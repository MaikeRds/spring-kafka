package com.example.producer;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequiredArgsConstructor
@RestController
public class ProducerController {
    public static final String SEU_TOPICO_MESSAGE = "SEU_TOPICO_MESSAGE";
    public static final String SEU_TOPICO_PRODUTO = "SEU_TOPICO_PRODUTO";


    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private KafkaTemplate<String, Produto> productKafkaTemplate;


    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaTemplate.send(SEU_TOPICO_MESSAGE, message);
        return "Mensagem enviada: " + message;
    }

    @PostMapping("/product")
    public String sendOroduct(@RequestBody Produto produto) {
        productKafkaTemplate.send(SEU_TOPICO_PRODUTO, produto);
        return "Produto enviado: " + produto.getNome();
    }
}

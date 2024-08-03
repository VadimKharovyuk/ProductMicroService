package com.example.productservice.kafka;

import com.example.productservice.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class kafkaProduser {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendProductUpdate(String message) {
        kafkaTemplate.send("product-topic", message);
    }



}

package com.example.demoeventuate;

import com.example.demoeventuate.dto.CreateTodoRequest;
import com.example.demoeventuate.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@Slf4j
//@Import({TramEventsPublisherConfiguration.class, TramMessageProducerJdbcConfiguration.class, NoopDuplicateMessageDetector.class})
class DemoEventuateApplicationTests {
    @Autowired
    private TodoService todoService;

    @Test
    void contextLoads() {
        CreateTodoRequest request = CreateTodoRequest.builder().title("hi there").build();
        UUID uuid = todoService.create(request);
        log.info("uid: {}", uuid);
    }

}

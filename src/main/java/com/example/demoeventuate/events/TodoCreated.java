package com.example.demoeventuate.events;


import io.eventuate.tram.events.common.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoCreated implements DomainEvent {
    private UUID id;
    private String title;
    private boolean completed;
    private int executionOrder;
}

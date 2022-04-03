package com.example.demoeventuate.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
@Access(AccessType.FIELD)
@Builder
public class Todo implements Serializable {
    @Id
    private UUID id;
    private String title;
    private boolean completed;
    private int executionOrder;
}

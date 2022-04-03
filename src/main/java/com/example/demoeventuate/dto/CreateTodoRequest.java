package com.example.demoeventuate.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTodoRequest implements Serializable {
    private String title;
    private boolean completed;
    private int order;
}

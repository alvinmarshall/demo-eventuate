package com.example.demoeventuate.events;


import java.util.UUID;

public class Utils {
    public static String generateUniqueString() {
        return UUID.randomUUID().toString();
    }
}

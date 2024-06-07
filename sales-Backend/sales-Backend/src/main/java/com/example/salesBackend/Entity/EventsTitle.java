package com.example.salesBackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsTitle {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long eventId;
    private String eventName;
    private int status;
}

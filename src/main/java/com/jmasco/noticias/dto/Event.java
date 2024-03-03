package com.jmasco.noticias.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "evento")
@Data
public class Event {

    @Id
    @JsonProperty("event_id")
    private int eventId;

    @JsonProperty("provider")
    private String provider;

}

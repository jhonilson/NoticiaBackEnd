package com.jmasco.noticias.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "launch")
@Data
public class Launch {

    @Id
    @JsonProperty("launch_id")
    private String launchId;

    @JsonProperty("provider")
    private String provider;
}

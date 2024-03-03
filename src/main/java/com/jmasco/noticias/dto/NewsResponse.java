package com.jmasco.noticias.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NewsResponse {

    @JsonProperty("count")
    private int count;

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private String previous;

    private List<Noticia> results;
}

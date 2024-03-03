package com.jmasco.noticias.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "noticia")
@Data
public class Noticia {

    @Id
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("news_site")
    private String newsSite;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("published_at")
    private Date publishedAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("featured")
    private boolean featured;

    private Date agregadoAt;

    @JsonProperty("launches")
    @OneToMany(mappedBy = "launchId")
    private List<Launch> launches;

    @JsonProperty("events")
    @OneToMany(mappedBy = "eventId")
    private List<Event> events;
}

package com.jmasco.noticias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {

    int totalElements;
    int totalPages;
    int size;
    List<Noticia> content;

}
package com.jmasco.noticias.repository;

import com.jmasco.noticias.dto.Noticia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository<T, ID> extends CrudRepository<Noticia, Integer> {
    Page<Noticia> findAll(Pageable pageable);
    Page<Noticia> findByTitleContaining(String title, Pageable pageable);
}

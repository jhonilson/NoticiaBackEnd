package com.jmasco.noticias.service.impl;

import com.jmasco.noticias.dto.Noticia;
import com.jmasco.noticias.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaServiceImpl implements FavoritoService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Override
    public List<Noticia> getFavoritos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Noticia> news = noticiaRepository.findAll(pageable);
        return news.getContent();
    }

    @Override
    public Noticia getFavoritoById(int id) {
        return (Noticia) noticiaRepository.findById(id).get();
    }

    @Override
    public Noticia createFavorito(Noticia noticia) {
        return (Noticia) noticiaRepository.save(noticia);
    }

    @Override
    public void deleteFavorito(int id) {
        noticiaRepository.deleteById(id);
    }

    @Override
    public Page<Noticia> getFavoritoByTitle(int page, int size, String field, String title) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, field));
        Page<Noticia> news = noticiaRepository.findByTitleContaining(title, pageable);
        return news;
    }

    public Page<Noticia> findNewsWithPagination(int offset, int pageSize, String field) {
        Page<Noticia> noticias = noticiaRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
        return noticias;
    }

}

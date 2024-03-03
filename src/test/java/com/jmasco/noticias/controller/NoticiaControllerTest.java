package com.jmasco.noticias.controller;

import com.jmasco.noticias.dto.Noticia;
import com.jmasco.noticias.repository.NoticiaRepository;
import com.jmasco.noticias.service.impl.NoticiaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NoticiaServiceImplTest {

    @Mock
    private NoticiaRepository noticiaRepository;

    @InjectMocks
    private NoticiaServiceImpl noticiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetFavoritos() {
        // Arrange
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        List<Noticia> noticias = new ArrayList<>();
        noticias.add(new Noticia());
        Page<Noticia> pageResult = new PageImpl<>(noticias);

        when(noticiaRepository.findAll(pageable)).thenReturn(pageResult);

        // Act
        List<Noticia> result = noticiaService.getFavoritos(page, size);

        // Assert
        assertEquals(noticias, result);
        verify(noticiaRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetFavoritoById() {
        // Arrange
        int id = 1;
        Noticia noticia = new Noticia();
        noticia.setId(id);

        when(noticiaRepository.findById(id)).thenReturn(Optional.of(noticia));

        // Act
        Noticia result = noticiaService.getFavoritoById(id);

        // Assert
        assertEquals(noticia, result);
        verify(noticiaRepository, times(1)).findById(id);
    }

    @Test
    void testCreateFavorito() {
        // Arrange
        Noticia noticia = new Noticia();

        when(noticiaRepository.save(noticia)).thenReturn(noticia);

        // Act
        Noticia result = noticiaService.createFavorito(noticia);

        // Assert
        assertEquals(noticia, result);
        verify(noticiaRepository, times(1)).save(noticia);
    }

    @Test
    void testDeleteFavorito() {
        // Arrange
        int id = 1;

        // Act
        noticiaService.deleteFavorito(id);

        // Assert
        verify(noticiaRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetFavoritoByTitle() {
        // Arrange
        int page = 0;
        int size = 10;
        String sort = "id";
        String title = "example";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        List<Noticia> noticias = new ArrayList<>();
        noticias.add(new Noticia());
        Page<Noticia> pageResult = new PageImpl<>(noticias);

        when(noticiaRepository.findByTitleContaining(title, pageable)).thenReturn(pageResult);

        // Act
        Noticia result = (Noticia) noticiaService.getFavoritoByTitle(page, size, sort, title);

        // Assert
        assertEquals(noticias.get(0), result);
        verify(noticiaRepository, times(1)).findByTitleContaining(title, pageable);
    }
}
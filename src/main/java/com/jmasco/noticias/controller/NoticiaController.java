package com.jmasco.noticias.controller;

import com.jmasco.noticias.dto.APIResponse;
import com.jmasco.noticias.dto.NewsResponse;
import com.jmasco.noticias.dto.Noticia;
import com.jmasco.noticias.service.impl.NoticiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NoticiaController {

    private static  final String URL = "https://api.spaceflightnewsapi.net/v4/articles";
    @Autowired
    NoticiaServiceImpl noticiaService;

    @GetMapping("/noticias")
    public NewsResponse getNoticias(@RequestParam(name = "limit", defaultValue = "10") int limit,
                                    @RequestParam(name = "ordering", defaultValue = "published_at") String ordering,
                                    @RequestParam(name = "offset", defaultValue = "1") int offset) {

        String url = URL + "?limit=" + limit + "&offset=" + offset + "&ordering=" + ordering;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NewsResponse> responseEntity = restTemplate.getForEntity(url, NewsResponse.class);
        return responseEntity.getBody();
    }

    @GetMapping("/noticias/byTitle")
    public NewsResponse getNoticiaByTitle(@RequestParam(name = "limit", defaultValue = "10") int limit,
                                    @RequestParam(name = "ordering", defaultValue = "published_at") String ordering,
                                    @RequestParam(name = "offset", defaultValue = "1") int offset,
                                    @RequestParam String search){

        String url = URL + "?limit=" + limit + "&offset=" + offset + "&ordering=" + ordering + "&search=" + search;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NewsResponse> responseEntity = restTemplate.getForEntity(url, NewsResponse.class);
        return responseEntity.getBody();
    }

    @GetMapping("/noticias/{id}")
    public Noticia getNoticiaById(@PathVariable int id){
        // URL del servicio
        String url = URL + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Noticia> responseEntity = restTemplate.getForEntity(url, Noticia.class);
        return responseEntity.getBody();
    }


    @GetMapping("/favoritos")
    public List<Noticia> getFavoritos(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size,
                                     @RequestParam(name = "sort", defaultValue = "agregadoAt") String sort){

        return noticiaService.getFavoritos(page, size);
    }

    @GetMapping("/favoritos/{id}")
    public Noticia getFavoritoById(@PathVariable int id){
        return noticiaService.getFavoritoById(id);
    }

    @GetMapping("/favoritos/buscar")
    public APIResponse<List<Noticia>> getFavoritoByTitle(@RequestParam(name = "offset", defaultValue = "0") int offset,
                                      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(name = "field", defaultValue = "agregadoAt") String field,
                                      @RequestParam(name = "cadena") String cadena){
     //   Pageable pageable = PageRequest.of(offset, pageSize );
        Page<Noticia> news = noticiaService.getFavoritoByTitle(offset, pageSize, field, cadena);
        return new APIResponse<>((int) news.getTotalElements(), news.getTotalPages(), news.getSize(), news.getContent());
        //return noticiaService.getFavoritoByTitle(offset, pageSize, field, cadena);
    }

    @PostMapping("/favoritos")
    public ResponseEntity<Noticia> createNoticia(@RequestBody Noticia noticia){
        System.out.println(noticia.toString());
        return new ResponseEntity<>(noticiaService.createFavorito(noticia), HttpStatus.CREATED);
    }

    @DeleteMapping("/favoritos/{id}")
    public ResponseEntity<Void> deleteNoticia(@PathVariable int id){
        noticiaService.deleteFavorito(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/favoritos/paginacion")
    public APIResponse<List<Noticia>> getFavoritosPaginacion(@RequestParam(name = "offset", defaultValue = "0") int offset,
                                                              @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                              @RequestParam(name = "field") String field){
        Page<Noticia> newsWithPagination = noticiaService.findNewsWithPagination(offset, pageSize, field);
        return new APIResponse<>((int) newsWithPagination.getTotalElements(), newsWithPagination.getTotalPages(), newsWithPagination.getSize(), newsWithPagination.getContent());
    }
}

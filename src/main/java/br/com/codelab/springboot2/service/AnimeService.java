//classe responsáveis pelas lógicas de negócios

package br.com.codelab.springboot2.service;

import br.com.codelab.springboot2.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    //private final AnimeRepository animeRepository;

    public List<Anime> ListAll(){
        return List.of( new Anime(1L, "Kuruko no basket"), new Anime(2L, "Blood of Zeus"));
    }
}

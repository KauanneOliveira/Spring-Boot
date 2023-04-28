//classe responsáveis pelas lógicas de negócios

package br.com.codelab.springboot2.service;

import br.com.codelab.springboot2.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AnimeService {

    private static List<Anime> animes;
    static {
        animes = new ArrayList<>( List.of( new Anime(1L, "Kuruko no basket"), new Anime(2L, "Blood of Zeus") ) );
    }

    //private final AnimeRepository animeRepository;

    public List<Anime> listAll(){
        return animes;
    }

    //orElseThrow é no caso de não encontrar nada
    // é no caso de vc tentar executar uma requisição na url passando um id e não encontra esse id, mts retornam o 404 (que é o status não encontrado)
    //mas o 404 não mostra oq não foi encontrado, por isso é melhor fazer assim
    public Anime findById(long id){
        return animes.stream()
                .filter( anime -> anime.getId().equals(id) )
                .findFirst()
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found") );
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        animes.add(anime);
        return anime;
    }

    public void delete(long id) {
        animes.remove( findById(id) ); //antes de remover, vai procurar se tem o id e dps vai remover
    }

    public void replace(Anime anime) {
        delete( anime.getId() );  //vai pesquisar, caso exista ele vai remover da lista
        animes.add(anime) ;       // e aqui ele vai adicionar o novo valor
    }
}

//classe responsáveis pelas lógicas de negócios

package br.com.codelab.springboot2.service;

import br.com.codelab.springboot2.domain.Anime;
import br.com.codelab.springboot2.repository.AnimeRepository;
import br.com.codelab.springboot2.requests.AnimePostRequestBody;
import br.com.codelab.springboot2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> listAll(){
        return animeRepository.findAll();
    }

    //orElseThrow é no caso de não encontrar nada
    // é no caso de vc tentar executar uma requisição na url passando um id e não encontra esse id, mts retornam o 404 (que é o status não encontrado)
    //mas o 404 não mostra oq não foi encontrado, por isso é melhor fazer assim
    public Anime findByIdOrThrowBadRequestException(long id){
        return animeRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found (Anime não encontrado)") );
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save( Anime.builder().name( animePostRequestBody.getName() ).build() );
    }

    public void delete(long id) {
        animeRepository.delete( findByIdOrThrowBadRequestException(id) );
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException( animePutRequestBody.getId() ); //se não encontrar o id ou vai mostrar uma exceção
        Anime anime = Anime.builder()
                .id( savedAnime.getId() )
                .name( animePutRequestBody.getName() )
                .build();  //o id vai ser o que está no bdd e td o resto vai ser atualizado

        animeRepository.save(anime);
    }
}

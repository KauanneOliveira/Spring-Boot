//classe responsáveis pelas lógicas de negócios

package br.com.codelab.springboot2.service;

import br.com.codelab.springboot2.domain.Anime;
import br.com.codelab.springboot2.exception.BadRequestException;
import br.com.codelab.springboot2.mapper.AnimeMapper;
import br.com.codelab.springboot2.repository.AnimeRepository;
import br.com.codelab.springboot2.requests.AnimePostRequestBody;
import br.com.codelab.springboot2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> listAll(){
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name){
        return animeRepository.findByName(name);
    }

    //orElseThrow é no caso de não encontrar nada
    // é no caso de vc tentar executar uma requisição na url passando um id e não encontra esse id, mts retornam o 404 (que é o status não encontrado)
    //mas o 404 não mostra oq não foi encontrado, por isso é melhor fazer assim
    public Anime findByIdOrThrowBadRequestException(long id){
        return animeRepository.findById(id)
                .orElseThrow( () -> new BadRequestException("Anime not Found (Anime não encontrado)") );
    }

    @Transactional //essa notação permite que o spring não comente a transação enquanto o método for finalizado
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save( AnimeMapper.INSTANCE.toAnime(animePostRequestBody) );
    }

    public void delete(long id) {
        animeRepository.delete( findByIdOrThrowBadRequestException(id) );
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException( animePutRequestBody.getId() ); //se não encontrar o id ou vai mostrar uma exceção
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId( savedAnime.getId() );
        animeRepository.save(anime);
    }
}


package br.com.codelab.springboot2.mapper;

import br.com.codelab.springboot2.domain.Anime;
import br.com.codelab.springboot2.requests.AnimePostRequestBody;
import br.com.codelab.springboot2.requests.AnimePutRequestBody;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-29T20:10:11-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@Component
public class AnimeMapperImpl extends AnimeMapper {

    @Override
    public Anime toAnime(AnimePostRequestBody animePostRequestBody) {
        if ( animePostRequestBody == null ) {
            return null;
        }

        Anime anime = new Anime();

        anime.setName( animePostRequestBody.getName() );

        return anime;
    }

    @Override
    public Anime toAnime(AnimePutRequestBody animePostRequestBody) {
        if ( animePostRequestBody == null ) {
            return null;
        }

        Anime anime = new Anime();

        anime.setId( animePostRequestBody.getId() );
        anime.setName( animePostRequestBody.getName() );

        return anime;
    }
}

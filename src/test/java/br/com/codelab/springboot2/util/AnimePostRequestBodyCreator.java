package br.com.codelab.springboot2.util;

import br.com.codelab.springboot2.domain.Anime;
import br.com.codelab.springboot2.requests.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {
    public static AnimePostRequestBody createAnimePostRequestBody(){
        return AnimePostRequestBody.builder()
                .name( AnimeCreator.createAnimeToBeSaved().getName() )
                .build();
    }
}

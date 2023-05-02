package br.com.codelab.springboot2.util;

import br.com.codelab.springboot2.requests.AnimePutRequestBody;

public class AnimePutRequestBodyCreator {
    public static AnimePutRequestBody createAnimePutRequestBody(){
        return AnimePutRequestBody.builder()
                .id( AnimeCreator.createValidUpdateAnime().getId() )
                .name( AnimeCreator.createValidUpdateAnime().getName() )
                .build();
    }
}

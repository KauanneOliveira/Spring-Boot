package br.com.codelab.springboot2.util;

import br.com.codelab.springboot2.domain.Anime;

//tá criando essa classe para ter um anime para ser usado no teste
//mas poderia ser método, porém teria que criar um em cada teste
public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }

    public static Anime createValidAnime(){
        return Anime.builder()
                .name("Hajime no Ippo")
                .id(1L)
                .build();
    }

    public static Anime createValidUpdateAnime(){
        return Anime.builder()
                .name("Hajime no Ippo 2")
                .id(1L)
                .build();
    }


}

package br.com.codelab.springboot2.controller;

import br.com.codelab.springboot2.domain.Anime;
import br.com.codelab.springboot2.requests.AnimePostRequestBody;
import br.com.codelab.springboot2.requests.AnimePutRequestBody;
import br.com.codelab.springboot2.service.AnimeService;
import br.com.codelab.springboot2.util.AnimeCreator;
import br.com.codelab.springboot2.util.AnimePostRequestBodyCreator;
import br.com.codelab.springboot2.util.AnimePutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks //é usado quando quer testar a classe em si
    private AnimeController  animeController; //no caso, essa é classe que vamos testar

    @Mock //utiliza para todas as classes que estão sendo utilizadas dentro do AnimeController
    private AnimeService animeServiceMock;

    @BeforeEach  //quer dizer que vai ser executado antes de cada um dos testes
    void setUp(){
        PageImpl<Anime> animePage = new PageImpl<>( List.of( AnimeCreator.createValidAnime() ) );

        BDDMockito.when( animeServiceMock.listAll(ArgumentMatchers.any() ) )
                .thenReturn(animePage);

        BDDMockito.when( animeServiceMock.listAllNonPageable() )
                .thenReturn( List.of( AnimeCreator.createValidAnime() ) );

        BDDMockito.when( animeServiceMock.findByIdOrThrowBadRequestException( ArgumentMatchers.anyLong() ) )
                .thenReturn( AnimeCreator.createValidAnime() );

        BDDMockito.when( animeServiceMock.findByName( ArgumentMatchers.anyString() ) )
                .thenReturn( List.of( AnimeCreator.createValidAnime() ) );

        BDDMockito.when( animeServiceMock.save( ArgumentMatchers.any(AnimePostRequestBody.class) ) )
                .thenReturn( AnimeCreator.createValidAnime() );

        BDDMockito.doNothing().when(animeServiceMock).replace( ArgumentMatchers.any(AnimePutRequestBody.class) );


        BDDMockito.doNothing().when(animeServiceMock).delete( ArgumentMatchers.anyLong() );
    }

    @Test
    @DisplayName("List returns list of anime inside page object when sucessful")
    void list_ReturnsListOfAnimesInsidePageObject_WhenSucessful(){
        String expectedName = AnimeCreator.createValidAnime().getName();
        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat( animePage.toList() )
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat( animePage.toList().get(0).getName() ).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("ListAll returns list of anime when sucessful")
    void listAll_ReturnsListOfAnimes_WhenSucessful(){
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animes = animeController.listAll().getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat( animes.get(0).getName() ).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns anime when sucessful")
    void findById_ReturnsAnimes_WhenSucessful(){
        Long expectedId = AnimeCreator.createValidAnime().getId();

        Anime animes = animeController.findById(1).getBody();

        Assertions.assertThat(animes).isNotNull();

        Assertions.assertThat( animes.getId() ).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns an empty list of anime when anime is not found")
    void findByName_ReturnsEmptyListOfAnimes_WhenAnimeIsNotFound(){
        BDDMockito.when( animeServiceMock.findByName( ArgumentMatchers.anyString() ) )
                .thenReturn( Collections.emptyList() );

        List<Anime> animes = animeController.findByName("anime").getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("replace update anime when sucessful")
    void replace_UpdateAnimes_WhenSucessful(){
        Assertions.assertThatCode( () -> animeController.replace(AnimePutRequestBodyCreator.createAnimePutRequestBody() ) )
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = animeController.replace( AnimePutRequestBodyCreator.createAnimePutRequestBody() );

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat( entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes anime when sucessful")
    void delete_RemovesAnimes_WhenSucessful(){
        Assertions.assertThatCode( () -> animeController.delete(1) )
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = animeController.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat( entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
package br.com.codelab.springboot2.repository;

import br.com.codelab.springboot2.domain.Anime;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest //permite criar um teste
@DisplayName("Tests for Anime Repository")
@Log4j2
//por padrão o nome do teste é o nome da classe, mas vc pode mudar o nome pelo @DisplayName
class AnimeRepositoryTest {

    @Autowired //pode usar sem problemas Autowired em testes
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persists anime when Sucessful")
    //forma recomendada de nomear um teste: nomeMetodo_precisaFazer_quandoAcontecer (Nome do método que quer testar, O que esse método precisa fazer, Quando isso deve acontecer)
    void save_PersistAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved); //está mandando o valor para o bdd
        Assertions.assertThat(animeSaved).isNotNull(); //está vereicando se ele foi retornado
        Assertions.assertThat(animeSaved.getId()).isNotNull(); // se ele tem o id
        Assertions.assertThat(animeSaved.getName()).isEqualTo( animeToBeSaved.getName() ); // se o nome dele é igual ao valor que pediu para ser salvo
    }
    @Test
    @DisplayName("Save updates anime when Sucessful")
    void save_UpdatesAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        animeSaved.setName("Overlord");
        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo( animeSaved.getName() );
    }

    @Test
    @DisplayName("Delete removes  anime when Sucessful")
    void delete_RemovesAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(animeSaved);

        //é para validar se foi deletado mesmo
        Optional<Anime> animeOptional = this.animeRepository.findById( animeSaved.getId() );
        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Find By Name returns list of anime when Sucessful")
    void findByName_ReturnsListOfAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();
        List<Anime> animes = this.animeRepository.findByName(name);

        //está checando se não está vazio e que temos(contém) o animeSaved
        Assertions.assertThat(animes).isNotEmpty()
                .contains(animeSaved);
    }
    @Test
    @DisplayName("Find By Name returns empty list when no anime is found")
    void findByName_ReturnsEmptyList_WhenAnimeIsNotFound(){
        List<Anime> animes = this.animeRepository.findByName("xaxa");

        Assertions.assertThat(animes).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowConstraintViolationException_WhenNameIsEmpty(){
        Anime anime = new Anime();

        //tem duas maneiras de fazer a exceção de quando o nome ta vazio

//        Assertions.assertThatThrownBy( () -> this.animeRepository.save(anime) )
//                .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy( () -> this.animeRepository.save(anime) )
                .withMessageContaining("The anime name cannot be empty");
    }

    //é necessário criar esse método para ter um anime para ser usado no teste
    //esse método vai sempre retornar um anime
    private Anime createAnime(){
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }
}
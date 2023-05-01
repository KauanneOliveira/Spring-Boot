package br.com.codelab.springboot2.client;

import br.com.codelab.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        
        //diferença entre método ForEntity e ForObject (tanto o get quanto o post): o ForEntity vai retornar o objeto dentro de um mapper e o ForObject vai retornar o objeto diretamente
        //RestTemplate exchange é usado para quando vc estiver usando uma Array ou não estiver passando  uma autenticação


                                                            //exibindo animes

        //vai fazer o mapeamento automáticamente na url mencionada, no tipo (class Anime), do valor da variavel colocada (8). Então vai mostrar os dados desse Anime no Terminal
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 8);
        //esse log que vai fazer mostrar no Terminal,
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 8);
        log.info(object);

        //vai exibir uma lista com todos os animes
        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info( Arrays.toString(animes) );


        //outra forma de exibir todos os animes (fazendo um cast)
        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        log.info( exchange.getBody() );
        
                                                        //adicionando animes

        Anime kingdom = Anime.builder().name("kingdom").build();
        //sintaxe postForObject: url, objeto, classe
        Anime kingdomSaved = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, Anime.class);
        log.info( "saved anime {}", kingdomSaved);

        Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
        ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.POST,
                new HttpEntity<>( samuraiChamploo, createJsonHeader() ),
                Anime.class);

        log.info("saved anime{}", samuraiChamplooSaved);
    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}

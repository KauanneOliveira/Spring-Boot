package br.com.codelab.springboot2.client;

import br.com.codelab.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        //vai fazer o mapeamento automáticamente na url mencionada, no tipo (class Anime), do valor da variavel colocada (8). Então vai mostrar os dados desse Anime no Terminal
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 8);
        //esse log que vai fazer mostrar no Terminal,
        log.info(entity); // exebição desse log: <200, Anime(id=8, name=Kuruko no basket), [Content-Type: "application/json", ...>

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 8);
        log.info(object); // exebição desse log: <200, Anime(id=8, name=Kuruko no basket)

        //vai exibir uma lista com todos os animes
        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info( Arrays.toString(animes) );

        //RestTemplate exchange é usado para quando vc estiver usando uma Array ou não estiver passando  uma autenticação

        //outra forma de exibir todos os animes (fazendo um cast)
        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        log.info( exchange.getBody() );
    }
}

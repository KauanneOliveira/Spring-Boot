// essa classe é bem simples, vai ter praticamente apenas os  endpoint

package br.com.codelab.springboot2.controller;

import br.com.codelab.springboot2.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.codelab.springboot2.domain.Anime;
import br.com.codelab.springboot2.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")  //por padrão, geralmente esse nome que vai na URL é no plural
@Log4j2
//@AllArgsConstructor - essa anotação do lombok cria o método contrutor com todos atributos que tem, sem precisar coloca-lo no código
@RequiredArgsConstructor //tbm vai criar um contrutor com os campos que são finais ( ex.: private final ...)
public class AnimeController {

    //@Autowired injeção de dependência via campos não é recomendado, o recomendado é usar no contrutor
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    //este método vai retornar uma lista de anime
    public List<Anime> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return animeService.ListAll();
    }

}

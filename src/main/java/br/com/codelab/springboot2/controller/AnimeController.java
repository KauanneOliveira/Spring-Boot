// essa classe é bem simples, vai ter praticamente apenas os  endpoint

package br.com.codelab.springboot2.controller;

import br.com.codelab.springboot2.requests.AnimePostRequestBody;
import br.com.codelab.springboot2.requests.AnimePutRequestBody;
import br.com.codelab.springboot2.service.AnimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Page<Anime>> list(Pageable pageable){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok( animeService.listAll(pageable) ); //o ResponseEntity vai retornar informações extras, como o status dessa requisição
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById( @PathVariable long id){
        return ResponseEntity.ok( animeService.findByIdOrThrowBadRequestException(id) );
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName( @RequestParam String name){
        return ResponseEntity.ok( animeService.findByName(name) );
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody){
        return new ResponseEntity<>( animeService.save(animePostRequestBody), HttpStatus.CREATED );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete( @PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody){
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
}

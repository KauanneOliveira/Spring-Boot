package springboot2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot2.domain.Anime;

import java.util.List;

@RestController
@RequestMapping("anime")
public class AnimeController {
    @GetMapping(path = "list")
    //este método vai retornar uma lista de anime
    public List<Anime> list(){
        return List.of( new Anime("DBZ"), new Anime("Berserk"));
    }

}

//representa oq temos do bdd

package br.com.codelab.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data  //vai gerar os métodos get, set, hashcode...
@AllArgsConstructor //vai gerar construtores com todos os valores (os métodos e construtores vão ser gerados na target/classes)
public class Anime {

    private Long id;
    private String name;

}

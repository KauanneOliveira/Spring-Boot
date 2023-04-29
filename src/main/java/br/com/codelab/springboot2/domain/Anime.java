//representa oq temos do bdd

package br.com.codelab.springboot2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //vai gerar os métodos get, set, hashcode...
@AllArgsConstructor //vai gerar construtores com todos os valores (os métodos e construtores vão ser gerados na target/classes)
@NoArgsConstructor //vai criar um contrutor sem argumentos, é necessario quando quer tranformar uma classe em Entity colocando o @Entity
@Entity
@Builder
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //o atributo abaixo será a chave primária
    private Long id;
    private String name;

}

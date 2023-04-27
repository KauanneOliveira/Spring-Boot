//é nessa classe vai ser a conexão direta com o bdd

package br.com.codelab.springboot2.repository;

import br.com.codelab.springboot2.domain.Anime;

import java.util.List;

public interface AnimeRepository {

    List<Anime> listAll();
}

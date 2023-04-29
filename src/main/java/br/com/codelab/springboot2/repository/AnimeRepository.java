//é nessa classe vai ser a conexão direta com o bdd

package br.com.codelab.springboot2.repository;

import br.com.codelab.springboot2.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}

//é nessa classe vai ser a conexão direta com o bdd

package br.com.codelab.springboot2.repository;

import br.com.codelab.springboot2.controller.CodeLabUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CodeLabAnimeUserRepository extends JpaRepository<CodeLabUser, Long> {
        CodeLabUser findByUsername(String username);

}



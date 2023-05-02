//é uma classe DTO

package br.com.codelab.springboot2.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostRequestBody {
    @NotEmpty(message = "The anime name cannot be empty(O nome do anime do não pode ser vazio)") //notação de validação (não passar campos vazios)
     private String name;
}
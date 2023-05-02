//é uma classe DTO

package br.com.codelab.springboot2.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimePutRequestBody {

    private Long id;
    private String name;
}

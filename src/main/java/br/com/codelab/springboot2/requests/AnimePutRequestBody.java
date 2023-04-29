//é uma classe DTO

package br.com.codelab.springboot2.requests;

import lombok.Data;

@Data
public class AnimePutRequestBody {

    private Long id;
    private String name;
}

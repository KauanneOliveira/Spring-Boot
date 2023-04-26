package springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration
//@ComponentScan //(basePackages = "springboot2") só é necessário escanear essa classe quando ela não está diretamente no pacote springboot2
//@Configuration
//essas três anotações são o que formam o básico do Spring para conseguir inicializar a sua aplicação, scannear suas configurações e os componentes que tem
//e a @SpringBootApplication suprime essas três anotações, trazendo mais consigo
@SpringBootApplication
public class ApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}

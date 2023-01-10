package br.com.alura.bytebank;

import br.com.alura.bytebank.messaging.EnvioPix;
import br.com.alura.bytebank.messaging.ProdutorPixBancoCentral;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import java.math.BigDecimal;

@EnableKafka
@SpringBootApplication
public class ByteBankApplication implements CommandLineRunner {

    private final ProdutorPixBancoCentral produtor;

    public ByteBankApplication(ProdutorPixBancoCentral produtor) {
        this.produtor = produtor;
    }

    public static void main(String[] args) {
        SpringApplication.run(ByteBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        produtor.envioMensagem(
                new EnvioPix(
                        "produtor",
                        "quemrecebe",
                        BigDecimal.valueOf(10.0)));
    }
}

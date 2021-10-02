package uz.napa.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class ClinicApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClinicApplication.class, args);
    }
}

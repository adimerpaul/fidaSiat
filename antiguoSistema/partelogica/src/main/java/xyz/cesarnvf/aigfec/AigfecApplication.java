package xyz.cesarnvf.aigfec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AigfecApplication {

	public static void main(String[] args) {
		SpringApplication.run(AigfecApplication.class, args);
	}

}

package se.juninatt.quizwiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "se.juninatt.quizwiz.model.entity")
public class QuizWizApp {
	public static void main(String[] args) {
		SpringApplication.run(QuizWizApp.class, args);
	}
}

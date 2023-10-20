package se.juninatt.quizwiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"se.juninatt.quizwiz.service",
		"se.juninatt.quizwiz.repository",
		})
@EntityScan(basePackages = "se.juninatt.quizwiz.model.entity")
public class QuizWizApp {
	public static void main(String[] args) {
		SpringApplication.run(QuizWizApp.class, args);
	}
}

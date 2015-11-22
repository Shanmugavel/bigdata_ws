package xyz.shanmugavel.poc.boot;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		System.out.println("Let's inspect the beans provided by Spring Boot!");
		String[] beanDefinitions = ctx.getBeanDefinitionNames();
		Arrays.sort(beanDefinitions);
		for (int i = 0; i < beanDefinitions.length; i++) {
			System.out.println(beanDefinitions[i]);
		}
	}
}
package com.cs130.connexity2;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Connexity2Application {

	public static void main(String[] args) {
		SpringApplication.run(Connexity2Application.class, args);
		
		/*ApplicationContext ctx = SpringApplication.run(Connexity2Application.class, args);
		
		System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) 
			System.out.println(beanName);
		}
	}*/
	}
}

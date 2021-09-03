package org.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan(basePackages = {"org.batch"})
public class LeanrBatchApplication implements CommandLineRunner{

	
	
	public static void main(String[] args) {
		SpringApplication.run(LeanrBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
	
	}

}

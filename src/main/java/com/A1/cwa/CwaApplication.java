package com.A1.cwa;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages = {
		"com.A1.cwa"
})
public class CwaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CwaApplication.class, args);
		
		
	}
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

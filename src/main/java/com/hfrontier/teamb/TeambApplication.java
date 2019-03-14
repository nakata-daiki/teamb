package com.hfrontier.teamb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("jp.co.hfrontier.teamb")
@MapperScan("jp.co.hfrontier.teamb.mapper")
public class TeambApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeambApplication.class, args);
	}

}


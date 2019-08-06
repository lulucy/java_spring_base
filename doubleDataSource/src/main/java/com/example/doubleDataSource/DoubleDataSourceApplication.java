package com.example.doubleDataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j

public class DoubleDataSourceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DoubleDataSourceApplication.class, args);
	}

	@Autowired
	@Qualifier("source2DataSource")
	private DataSource source2DataSource;

	@Override
	public void run(String... strings) throws Exception {
		System.out.println(source2DataSource.getConnection().toString());
		System.out.println(source2DataSource.toString());
	}
}

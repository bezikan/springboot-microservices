package com.oceanboa.stock.db2service;

import com.oceanboa.stock.db2service.model.Quote;
import com.oceanboa.stock.db2service.repository.QuotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;


@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
public class DB2ServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(DB2ServiceApplication.class);

    @Value("${ALLOWED_CORS:http://localhost:3000}")
    private static String allowedCORS;

	public static void main(String[] args) {
		SpringApplication.run(DB2ServiceApplication.class, args);
	}

    @Autowired
    private QuotesRepository quotesRepository;

    @Autowired
    private Environment env;

	@Bean
	public CommandLineRunner loadData(QuotesRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Quote("Bob", "GOOG"));
			repository.save(new Quote("Bob", "MSFT"));
			repository.save(new Quote("Bob", "TSLA"));

			log.info("Initial Quotes found with findAll():");
			log.info("-------------------------------");
			for (Quote q : repository.findAll()) {
				log.info(q.toString());
			}
			log.info("");
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {



		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

                String allowedCors = env.getProperty("allowed_cors");

				registry.addMapping("/**")
						.allowedOrigins(allowedCors);
			}
		};
	}
}

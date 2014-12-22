package sample.data.jpa;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = "sample.data.jpa")
public class DatabaseConfig {

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder entityManagerBuilder,
			DataSource dataSource) {
		return entityManagerBuilder.dataSource(dataSource)
				.packages("sample.data.jpa.domain").persistenceUnit("FOO-BAR")
				.build();
	}

}

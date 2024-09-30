
package com.example.la.common.usuario.config;

import java.util.Properties;

import javax.sql.DataSource;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Generated
@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.la.common.usuario.repository")
@PropertySources({
    @PropertySource("classpath:bd.properties")
})
public class DatabaseConfiguration{

	@Autowired
	private Environment env;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setJpaProperties(additionalProperties());
		
		em.setPackagesToScan("com.example.la.common.usuario");
		em.setJpaVendorAdapter(vendorAdapter);

		return em;
	}

	@Bean
	public DataSource dataSource() {
		System.out.println("Configuration");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));

		String profile=env.getProperty("spring.profiles.active");
		if(profile!=null && profile.equalsIgnoreCase("docker")){
			dataSource.setUrl(env.getProperty("url.docker"));
		}
		else{
			dataSource.setUrl(env.getProperty("url.local"));
		}

		//dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		return properties;
	}

}

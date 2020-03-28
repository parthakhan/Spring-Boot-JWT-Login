package com.partha.library.configuration;



import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	@Value("${spring.datasource.driverClassName}")
	private String DRIVER;

	@Value("${spring.datasource.password}")
	private String PASSWORD;

	@Value("${spring.datasource.url}")
	private String URL;

	@Value("${spring.datasource.username}")
	private String USERNAME;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String DIALECT;

	@Value("${spring.jpa.hibernate.show-sql}")
	private String SHOW_SQL;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private String PACKAGES_TO_SCAN;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}

	/*
	 * @Bean public LocalSessionFactoryBean sessionFactory() {
	 * LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	 * sessionFactory.setDataSource(dataSource());
	 * sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN); Properties
	 * hibernateProperties = new Properties();
	 * hibernateProperties.put("spring.jpa.properties.hibernate.dialect", DIALECT);
	 * hibernateProperties.put("spring.jpa.hibernate.show-sql", SHOW_SQL);
	 * hibernateProperties.put("spring.jpa.hibernate.ddl-auto", HBM2DDL_AUTO);
	 * sessionFactory.setHibernateProperties(hibernateProperties);
	 * 
	 * return sessionFactory; }
	 */

	/*
	 * @Bean public HibernateTransactionManager transactionManager() {
	 * HibernateTransactionManager transactionManager = new
	 * HibernateTransactionManager();
	 * transactionManager.setSessionFactory(sessionFactory().getObject()); return
	 * transactionManager; }
	 */
	
	@Bean
	public PlatformTransactionManager transactionManager() {
	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setDataSource(dataSource());
	    txManager.setEntityManagerFactory(entityManagerFactory().getObject());
	    return txManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		// JpaVendorAdapteradapter can be autowired as well if it's configured in //
		// application.properties.
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter); // Add package to scan for entities.
		factory.setPackagesToScan("com.partha.library");
		factory.setDataSource(dataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("spring.jpa.properties.hibernate.dialect", DIALECT);
		hibernateProperties.put("spring.jpa.hibernate.show-sql", SHOW_SQL);
		hibernateProperties.put("spring.jpa.hibernate.ddl-auto", HBM2DDL_AUTO);
		factory.setJpaProperties(hibernateProperties);
		return factory;
	}

}


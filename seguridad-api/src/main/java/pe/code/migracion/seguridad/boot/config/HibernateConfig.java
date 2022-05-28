package pe.code.migracion.seguridad.boot.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	@Autowired
	HibernateConfigProperties hibernateProperties;

	/**
	 * Data source : Propiedades leidas desde el archivoo .YML o .Proerties
	 * 
	 * @return
	 */
	@Bean
	@Primary
	public HikariDataSource getDataSource() {
		HikariConfig c = new HikariConfig();
		
		/** MSSQL **/
//		c.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
//		c.setConnectionTestQuery("SELECT GETDATE()");
//		c.setMaximumPoolSize(32);
//		c.setJdbcUrl("jdbc:jtds:sqlserver://10.10.1.21:1212;databaseName=SPRING_DBW;");
//		c.getDataSourceProperties().put("user", "local");
//		c.getDataSourceProperties().put("password", "local2017");
//		c.getDataSourceProperties().put("cacheMetaData", true);
		
		
		/** POSTGRES **/
		c.setDriverClassName("org.postgresql.Driver");
		c.setConnectionTestQuery("SELECT current_timestamp");
		c.setMaximumPoolSize(32);
		c.setJdbcUrl("jdbc:postgresql://localhost:5432/codemigracion");
		c.getDataSourceProperties().put("user", "postgres");
		c.getDataSourceProperties().put("password", "local");
		c.getDataSourceProperties().put("cacheMetaData", true);

		HikariDataSource hds = new HikariDataSource(c);

		return hds;
	}

	/**
	 * Session Factory como BEAN, diferenciar la clase : "LocalSessionFactoryBean"
	 * de "HibernateJpaSessionFactoryBean", además set las propiedades respectivas y
	 * el DATASOURCE
	 * 
	 * @return
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFact = new LocalSessionFactoryBean();
		sessionFact.setDataSource(getDataSource());
		sessionFact.setPackagesToScan(hibernateProperties.getPackagesToScan());
		sessionFact.setHibernateProperties(additionalProperties());
		return sessionFact;
	}

	public Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", hibernateProperties.getDialect());
		properties.setProperty("hibernate.show_sql", hibernateProperties.getShow_sql());
		properties.setProperty("hibernate.format_sql", hibernateProperties.getFormat_sql());

		return properties;
	}

	/**
	 * Como Autowired, manejara las Transacciones de @Transactional Diferenciar la
	 * clase : "HibernateTransactionManager"
	 * 
	 * @param sessionFactory
	 * @return
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}

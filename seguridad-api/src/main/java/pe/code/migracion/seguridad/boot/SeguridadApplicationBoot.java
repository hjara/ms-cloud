package pe.code.migracion.seguridad.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
@SpringBootApplication(scanBasePackages = { "pe.code.migracion" })
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class SeguridadApplicationBoot {

	public static void main(String[] args) {
		SpringApplication.run(SeguridadApplicationBoot.class, args);
	}

}

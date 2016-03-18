package com.thiraviyum.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Configuration
@ConditionalOnClass({Java8TimeDialect.class})
public class ThymeleafJava8TimeDialectConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
}

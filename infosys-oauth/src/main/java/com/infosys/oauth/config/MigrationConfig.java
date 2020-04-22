package com.infosys.oauth.config;

import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
@Slf4j
public class MigrationConfig {

	@Bean
	public SpringLiquibase liquibase(@Qualifier("taskExecutor") Executor executor,
			@LiquibaseDataSource ObjectProvider<DataSource> liquibaseDataSource,
			LiquibaseProperties liquibaseProperties, ObjectProvider<DataSource> dataSource,
			DataSourceProperties dataSourceProperties) {

		// If you don't want Liquibase to start asynchronously, substitute by this:
		// SpringLiquibase liquibase =
		// SpringLiquibaseUtil.createSpringLiquibase(liquibaseDataSource.getIfAvailable(),
		// liquibaseProperties, dataSource.getIfUnique(), dataSourceProperties);
		SpringLiquibase liquibase = SpringLiquibaseUtil.createAsyncSpringLiquibase(executor,
				liquibaseDataSource.getIfAvailable(), liquibaseProperties, dataSource.getIfUnique(),
				dataSourceProperties);
		liquibase.setChangeLog("classpath:db/master.xml");
		liquibase.setContexts(liquibaseProperties.getContexts());
		liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
		liquibase.setLiquibaseSchema(liquibaseProperties.getLiquibaseSchema());
		liquibase.setLiquibaseTablespace(liquibaseProperties.getLiquibaseTablespace());
		liquibase.setDatabaseChangeLogLockTable(liquibaseProperties.getDatabaseChangeLogLockTable());
		liquibase.setDatabaseChangeLogTable(liquibaseProperties.getDatabaseChangeLogTable());
		liquibase.setDropFirst(liquibaseProperties.isDropFirst());
		liquibase.setLabels(liquibaseProperties.getLabels());
		liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
		liquibase.setRollbackFile(liquibaseProperties.getRollbackFile());
		liquibase.setTestRollbackOnUpdate(liquibaseProperties.isTestRollbackOnUpdate());
		liquibase.setShouldRun(liquibaseProperties.isEnabled());
		log.debug("Configuring Liquibase");
		return liquibase;
	}
}

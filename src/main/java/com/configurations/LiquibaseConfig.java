package com.configurations;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;

public class LiquibaseConfig {

  @Autowired
  @Qualifier("tenantDataSource")
  private DataSource tenantDataSource;

  @Bean
  public LiquibaseProperties primaryLiquibaseProperties() {
    return new LiquibaseProperties();
  }

  @Bean(name = "liquibase")
  public SpringLiquibase primaryLiquibase() {
    return (SpringLiquibase) springLiquibase(tenantDataSource, primaryLiquibaseProperties());
  }

  private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource);
    liquibase.setChangeLog(properties.getChangeLog());
    liquibase.setContexts(properties.getContexts());
    liquibase.setDefaultSchema(properties.getDefaultSchema());
    liquibase.setDropFirst(properties.isDropFirst());
    liquibase.setShouldRun(properties.isEnabled());
    liquibase.setLabels(properties.getLabels());
    liquibase.setChangeLogParameters(properties.getParameters());
    liquibase.setRollbackFile(properties.getRollbackFile());
    return liquibase;
  }
}

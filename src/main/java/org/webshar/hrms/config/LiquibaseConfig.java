package org.webshar.hrms.config;

import javax.activation.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;

public class LiquibaseConfig {

  @Autowired
  @Qualifier("tenantDataSource")
  private DataSource tenantDataSource;

  private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource((javax.sql.DataSource) dataSource);
    liquibase.setChangeLog(properties.getChangeLog());
    liquibase.setContexts(properties.getContexts());
    liquibase.setDefaultSchema(properties.getDefaultSchema());
    liquibase.setDropFirst(properties.isDropFirst());
    liquibase.setChangeLog("/db/master/master.xml");
    liquibase.setShouldRun(properties.isEnabled());
    liquibase.setLabels(properties.getLabels());
    liquibase.setChangeLogParameters(properties.getParameters());
    liquibase.setRollbackFile(properties.getRollbackFile());
    return liquibase;
  }

  @Bean
  public LiquibaseProperties primaryLiquibaseProperties() {
    return new LiquibaseProperties();
  }

  @Bean(name = "liquibase")
  public SpringLiquibase primaryLiquibase() {
    return springLiquibase(tenantDataSource, primaryLiquibaseProperties());
  }
}

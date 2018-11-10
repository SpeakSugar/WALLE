package com.cloud.base.springconfig;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.cloud.base.*")
@EnableTransactionManagement// 开启事务及对注解@Transactional的支持
public class SpringConfig {

    // 实体管理工厂配置
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf =new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        emf.setPackagesToScan("com.cloud.base.model");//替代persistences.xml,对该路径下的@Entity扫描初始化

        Map<String,String> jpsMaps =new HashMap<String,String>();//实体管理器参数的配置
        jpsMaps.put("hibernate.show_sql","true");
        jpsMaps.put("hibernate.hbm2ddl.auto","update");
        jpsMaps.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        emf.setJpaPropertyMap(jpsMaps);
        return emf;
    }

    // JPA的实体适配器
    @Bean
    public JpaVendorAdapter hibernateJpaVendorAdapter(){
        HibernateJpaVendorAdapter jpaVA =new HibernateJpaVendorAdapter();
        jpaVA.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");// MySQL平台指定
        return jpaVA;
    }

    // 数据源的配置
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource =new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mysqltest");
        dataSource.setUsername("root");
        dataSource.setPassword("123");
        dataSource.setDefaultAutoCommit(false);
        return dataSource;
    }

    // Spring Data JPA事务配置
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTm =new JpaTransactionManager();
        jpaTm.setEntityManagerFactory(entityManagerFactory().getObject());
        return jpaTm;
    }













}

package lk.ijse.pos.main;


import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("file:${user.dir}/resource/application.properties")
public class HibernateConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        lsfb.setPackagesToScan("lk.ijse.pos.entity");
        lsfb.setHibernateProperties(hibernateProperties());
        return lsfb;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource bds = new BasicDataSource();

        bds.setDriverClassName(env.getRequiredProperty("hibernate.connection.driver_class"));
        bds.setUrl(env.getRequiredProperty("hibernate.connection.url"));
        bds.setUsername(env.getRequiredProperty("hibernate.connection.username"));
        bds.setPassword(env.getRequiredProperty("hibernate.connection.password"));

//        bds.setInitialSize(Integer.parseInt(env.getRequiredProperty("hibernate.initial_size")));
        bds.setInitialSize(env.getRequiredProperty("hibernate.initial_size", Integer.class));
        bds.setMaxTotal(env.getRequiredProperty("hibernate.max_total", Integer.class));
        bds.setMaxIdle(env.getRequiredProperty("hibernate.max_idle", Integer.class));

        return bds;
    }

    public Properties hibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        prop.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        prop.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return prop;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sf) {
        return new HibernateTransactionManager(sf);
    }
}

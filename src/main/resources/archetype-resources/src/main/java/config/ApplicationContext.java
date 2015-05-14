package config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import rugal.sample.core.repository.RepositoryPackage;

/**
 * Java based application context configuration class.
 *
 * @author Rugal Bernstein
 * @since 0.2
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = RepositoryPackage.class)
@ComponentScan(value = "rugal.sample.core")
@PropertySource("classpath:jdbc.properties")
public class ApplicationContext extends AbstractMongoConfiguration
{

    @Autowired
    private Environment env;

    @Override
    public String getDatabaseName()
    {
        return env.getProperty("jdbc.db");
    }

    @Override
    @Bean
    public MongoClient mongo() throws Exception
    {
        return new MongoClient(env.getProperty("jdbc.server"), Integer.parseInt(env.getProperty("jdbc.port")));
    }

    @Override
    protected String getMappingBasePackage()
    {
        return env.getProperty("jdbc.mapping");
    }

}

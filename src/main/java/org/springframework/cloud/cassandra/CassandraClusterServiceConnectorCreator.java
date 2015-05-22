package org.springframework.cloud.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.Session;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.util.StringUtils;

/**
 * Created by vcarvalho on 5/7/15.
 */
public class CassandraClusterServiceConnectorCreator extends AbstractServiceConnectorCreator<Session, CassandraClusterServiceInfo> {
    @Override
    public Session create(CassandraClusterServiceInfo cassandraClusterServiceInfo, ServiceConnectorConfig serviceConnectorConfig) {
        CassandraConnectorConfig config = (CassandraConnectorConfig)serviceConnectorConfig;
        CassandraClusterFactoryBean clusterFactory = new CassandraClusterFactoryBean();
        clusterFactory.setContactPoints(StringUtils.collectionToCommaDelimitedString(cassandraClusterServiceInfo.getNodes()));
        if(cassandraClusterServiceInfo.getUsername() != null && !cassandraClusterServiceInfo.getUsername().isEmpty()) {
            clusterFactory.setUsername(cassandraClusterServiceInfo.getUsername());
            clusterFactory.setPassword(cassandraClusterServiceInfo.getPassword());
            clusterFactory.setAuthProvider(new PlainTextAuthProvider(cassandraClusterServiceInfo.getUsername(), cassandraClusterServiceInfo.getPassword()));
        }
        clusterFactory.setPort(cassandraClusterServiceInfo.getCqlPort());
        Session session = null;
        Cluster cluster = null;
        try {
            clusterFactory.afterPropertiesSet();
            cluster = clusterFactory.getObject();
            CassandraSessionFactoryBean sessionFactory = new CassandraSessionFactoryBean();
            sessionFactory.setCluster(cluster);
            sessionFactory.setConverter(converter(config));
            sessionFactory.setSchemaAction(SchemaAction.NONE);
            sessionFactory.setKeyspaceName(cassandraClusterServiceInfo.getKeyspace());
            sessionFactory.afterPropertiesSet();
            session = sessionFactory.getObject();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return session;
    }

    public CassandraConverter converter(CassandraConnectorConfig config) {
        CassandraConverter converter = (config == null || config.getConverter() == null) ? new MappingCassandraConverter(mappingContext(config)) : config.getConverter();
        return converter;
    }

    private CassandraMappingContext mappingContext(CassandraConnectorConfig config) {
        CassandraMappingContext context = (config == null || config.getContext() == null) ? new BasicCassandraMappingContext() : config.getContext();
        return context;
    }
}

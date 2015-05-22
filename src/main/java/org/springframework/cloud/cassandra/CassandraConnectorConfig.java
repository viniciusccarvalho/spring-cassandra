package org.springframework.cloud.cassandra;

import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;

/**
 * Created by vcarvalho on 5/7/15.
 */
public class CassandraConnectorConfig implements ServiceConnectorConfig {

    private CassandraMappingContext context;
    private CassandraConverter converter;

    public CassandraMappingContext getContext() {
        return context;
    }

    public void setContext(CassandraMappingContext context) {
        this.context = context;
    }

    public CassandraConverter getConverter() {
        return converter;
    }

    public void setConverter(CassandraConverter converter) {
        this.converter = converter;
    }
}

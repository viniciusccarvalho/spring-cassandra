package org.springframework.cloud.cassandra;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

import java.util.List;
import java.util.Map;

/**
 * Created by vcarvalho on 5/7/15.
 */
public class CassandraClusterServiceInfoCreator  extends CloudFoundryServiceInfoCreator<CassandraClusterServiceInfo>{


    public CassandraClusterServiceInfoCreator() {
        super(new Tags("cassandra"));
    }

    @Override
    public CassandraClusterServiceInfo createServiceInfo(Map<String, Object> stringObjectMap) {
        Map<String,Object> credentials = (Map<String, Object>) stringObjectMap.get("credentials");
        String id = (String) stringObjectMap.get("name");
        String keyspace = getStringFromCredentials(credentials,"keyspace_name");
        String username = getStringFromCredentials(credentials,"username");
        String password = getStringFromCredentials(credentials,"password");
        Integer thriftPort = (Integer) credentials.get("thrift_port");
        Integer cqlPort = (Integer) credentials.get("cql_port");
        List<String> nodeIps = (List<String>) credentials.get("node_ips");

        return new CassandraClusterServiceInfo(id,nodeIps,username,password,keyspace,cqlPort);
    }
}

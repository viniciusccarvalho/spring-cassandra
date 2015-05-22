package org.springframework.cloud.cassandra;

import org.springframework.cloud.service.BaseServiceInfo;
import org.springframework.cloud.service.ServiceInfo;

import java.util.List;

/**
 * Created by vcarvalho on 5/7/15.
 */
public class CassandraClusterServiceInfo extends BaseServiceInfo{



    private final Integer cqlPort;
    private String username;
    private String password;
    private String keyspace;
    private List<String> nodes;

    public CassandraClusterServiceInfo(String id, List<String> nodes, String username, String password, String keyspace, Integer cqlPort) {
        super(id);
        this.nodes = nodes;
        this.username = username;
        this.password = password;
        this.keyspace = keyspace;
        this.cqlPort = cqlPort;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public List<String> getNodes() {
        return nodes;
    }
    public Integer getCqlPort() {
        return cqlPort;
    }
}

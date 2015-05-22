package org.springframework.cassandra.repo;

import org.springframework.cassandra.domain.WeatherStation;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;

/**
 * Created by vcarvalho on 5/22/15.
 */
public interface WeatherStationRepository extends CassandraRepository<WeatherStation> {

    @Query("select * from temperature where weatherstation_id = ?0")
    public List<WeatherStation> findById( String id);



}

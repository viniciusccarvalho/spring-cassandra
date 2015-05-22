package org.springframework.cassandra.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;

/**
 * Created by vcarvalho on 5/22/15.
 */
@Table("temperature")
public class WeatherStation {

    public WeatherStation(){}

    @JsonCreator
    public WeatherStation(@JsonProperty("id") String stationId, @JsonProperty("event_time") Long timestamp, @JsonProperty("temperature") Double temperature){
        this.id = new WeatherStationId(stationId,timestamp);
        this.temperature = temperature;
    }


    @PrimaryKey
    private WeatherStationId id;

    @JsonIgnore
    public WeatherStationId getId() {
        return id;
    }

    public void setId(WeatherStationId id) {
        this.id = id;
    }

    private Double temperature;

    public String getStationId(){
        return this.id.getStationId();
    }

    public Long getEventTime(){
        return this.getId().getEventTime();
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @PrimaryKeyClass
    public static class WeatherStationId implements Serializable{

        public WeatherStationId(){}

        public WeatherStationId(String stationId, Long timestamp) {
            this.stationId = stationId;
            this.eventTime = timestamp;
        }

        @PrimaryKeyColumn(name = "weatherstation_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private String stationId;
        @PrimaryKeyColumn(name = "event_time", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
        private Long eventTime;

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public Long getEventTime() {
            return eventTime;
        }

        public void setEventTime(Long timestamp) {
            this.eventTime = timestamp;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WeatherStationId that = (WeatherStationId) o;

            if (!stationId.equals(that.stationId)) return false;
            return eventTime.equals(that.eventTime);

        }

        @Override
        public int hashCode() {
            int result = stationId.hashCode();
            result = 31 * result + eventTime.hashCode();
            return result;
        }
    }
}

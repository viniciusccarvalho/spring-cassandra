package org.springframework.cassandra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.domain.WeatherStation;
import org.springframework.cassandra.repo.WeatherStationRepository;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vcarvalho on 5/22/15.
 */
@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherStationRepository repository;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addEntry(@RequestBody WeatherStation weatherStation){
        repository.save(weatherStation);
        ResponseEntity entity = ResponseEntity.ok().build();
        return entity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<WeatherStation>> findById(@PathVariable("id") String id){
        List<WeatherStation> stations = repository.findById(id);
        ResponseEntity<List<WeatherStation>> response = new ResponseEntity<List<WeatherStation>>(stations,HttpStatus.OK);
        return response;

    }


}

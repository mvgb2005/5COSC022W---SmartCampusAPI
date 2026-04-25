package com.example.resource;

import com.example.dao.GenericDAO;
import com.example.dao.MockDatabase;
import com.example.model.Sensor;
import com.example.model.SensorReading;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(MockDatabase.SENSORS);
    private GenericDAO<SensorReading> readingDAO = new GenericDAO<>(MockDatabase.READINGS);
    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }
    
    @GET
    public List<SensorReading> getReadings() {
        return readingDAO.getAll().stream().filter(r -> r.getSensorId().equals(sensorId)).collect(Collectors.toList());
    }

    @POST
    public void addReading(SensorReading reading) {
        Sensor sensor = sensorDAO.getById(sensorId);
        if (sensor == null) {
            throw new SensorNotFoundException("sensor not found");
        }
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorNotAvailableException("sensor under maintenance");
        }
        reading.setSensorId(sensorId);
        sensor.setCurrentValue(reading.getValue());
        readingDAO.add(reading);
    }
}

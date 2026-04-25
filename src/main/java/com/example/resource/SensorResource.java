package com.example.resource;

import com.example.dao.GenericDAO;
import com.example.dao.MockDatabase;
import com.example.model.Sensor;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(MockDatabase.SENSORS);

    @GET
    public List<Sensor> getAllSensors(@QueryParam("type") String type) {
        List<Sensor> sensors = sensorDAO.getAll();
        if (type == null) {
            return sensors;
        }
        return sensors.stream().filter(sensor -> sensor.getType().equalsIgnoreCase(type)).collect(java.util.stream.Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Sensor getSensor(@PathParam("id") String id) {
        return sensorDAO.getById(id);
    }

    @POST
    public void addSensor(Sensor sensor) {
        boolean roomExists = MockDatabase.ROOMS.stream().anyMatch(room -> room.getId().equals(sensor.getRoomId()));
        if (! roomExists) {
            throw new RoomNotExistException("room doesnt exist");
        }
        sensorDAO.add(sensor);
    }

    @PUT
    @Path("/{id}")
    public void updateSensor(@PathParam("id") String id, Sensor sensor) {
        sensor.setId(id);
        sensorDAO.update(sensor);
    }

    @DELETE
    @Path("/{id}")
    public void deleteSensor(@PathParam("id") String id) {
        sensorDAO.delete(id);
    }
    
    @Path("/{id}/readings")
    public SensorReadingResource getReadingResource(@PathParam("id") String id) {
    return new SensorReadingResource(id);
}
}
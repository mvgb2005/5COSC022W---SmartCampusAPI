package com.example.resource;

import com.example.dao.GenericDAO;
import com.example.dao.MockDatabase;
import com.example.model.Room;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    private GenericDAO<Room> roomDAO = new GenericDAO<>(MockDatabase.ROOMS);

    @GET
    public List<Room> getAllRooms() {
        return roomDAO.getAll();
    }

    @GET
    @Path("/{id}")
    public Room getRoom(@PathParam("id") String id) {
        return roomDAO.getById(id);
    }

    @POST
    public void addRoom(Room room) {
        roomDAO.add(room);
    }

    @PUT
    @Path("/{id}")
    public void updateRoom(@PathParam("id") String id, Room room) {
        room.setId(id);
        roomDAO.update(room);
    }

    @DELETE
    @Path("/{id}")
    public void deleteRoom(@PathParam("id") String id) {
        boolean hasSensors = MockDatabase.SENSORS.stream().anyMatch(sensor -> sensor.getRoomId().equals(id));
        if (hasSensors) {
            throw new RoomNotEmptyException("room cant be deleted as it still has sensors linked");
        }
        roomDAO.delete(id);
    }
}
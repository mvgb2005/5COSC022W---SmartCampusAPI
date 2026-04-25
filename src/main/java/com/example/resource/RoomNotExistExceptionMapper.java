package com.example.resource;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class RoomNotExistExceptionMapper implements ExceptionMapper<RoomNotExistException> {

    @Override
    public Response toResponse(RoomNotExistException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return Response.status(422).entity(error).type(MediaType.APPLICATION_JSON).build(); // http 422
    }
}
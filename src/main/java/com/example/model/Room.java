package com.example.model;

import java.util.List;
import java.util.ArrayList;

public class Room  implements BaseModel {
    private String id; // Unique identifier
    private String name;  // Human-readable name
    private int capacity; // Maximum occupancy for safetyregulations
    private List<String> sensorIds = new ArrayList<>(); // Collection of IDs of sensors deployed in this room

    public Room() {}

    public Room(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String getId() { return id; }
    @Override
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getCapacity() { return capacity; }
    public void setCapacity (int capacity) {this.capacity = capacity; }
    
    public List<String> getSensorIds() { return sensorIds; }
    public void setSensorIds(List<String> sensorIds) { this.sensorIds = sensorIds; }
}
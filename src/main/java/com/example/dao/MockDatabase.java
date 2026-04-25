package com.example.dao;

import com.example.model.Room;
import com.example.model.Sensor;
import com.example.model.SensorReading;

import java.util.ArrayList;
import java.util.List;

public class MockDatabase {
    public static final List<Room> ROOMS = new ArrayList<>();
    public static final List<Sensor> SENSORS = new ArrayList<>();
    public static final List<SensorReading> READINGS = new ArrayList<>();

    static {
        // Rooms
        ROOMS.add(new Room("LIB-301", "Library Quiet Study", 100));
        ROOMS.add(new Room("LAB-105", "Computer Lab", 30));

        // Sensors
        SENSORS.add(new Sensor("TEMP-011", "Temperature", "ACTIVE", 21.5, "LIB-301"));
        SENSORS.add(new Sensor("CO2-001", "CO2", "MAINTENANCE", 0, "LAB-105"));
        SENSORS.add(new Sensor("OCC-010", "Occupancy", "OFFLINE", 0, "LAB-105"));

        // Readings
        READINGS.add(new SensorReading("READ-001", System.currentTimeMillis(), 21.5, "TEMP-011"));
    }
}
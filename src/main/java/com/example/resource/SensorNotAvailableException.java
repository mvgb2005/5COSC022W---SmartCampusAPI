/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Owner
 */
public class SensorNotAvailableException extends RuntimeException { // business rule: sensor is in MAINTENANCE and cannot take readings
    public SensorNotAvailableException(String message) {
        super(message);
    }
}   
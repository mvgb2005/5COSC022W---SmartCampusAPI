/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Owner
 */
public class SensorNotFoundException extends RuntimeException { // business rule: sesnor does not exist
    public SensorNotFoundException(String message) {
        super(message);
    }
}
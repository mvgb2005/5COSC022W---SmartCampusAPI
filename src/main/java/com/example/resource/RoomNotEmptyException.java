/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Owner
 */
public class RoomNotEmptyException extends RuntimeException { // business rule: cant delete room with sensors
    public RoomNotEmptyException(String message) {
        super(message);
    }
}

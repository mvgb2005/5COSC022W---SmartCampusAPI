/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author Owner
 */
public class RoomNotExistException extends RuntimeException { // busniess rule: cannot link sesnor to room that does not exist
    public RoomNotExistException(String message) {
        super(message);
    }
}

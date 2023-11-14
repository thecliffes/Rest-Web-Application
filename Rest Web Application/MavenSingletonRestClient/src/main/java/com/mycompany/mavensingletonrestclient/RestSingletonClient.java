/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavensingletonrestclient;

/**
 *
 * @author bencl
 */
public class RestSingletonClient {
    public static void main(String[] args){
        ResourceConnector connector = new ResourceConnector();
        connector.addMountain("snowden", "snowdonia", "wales", 1080, "n");
        connector.addMountain("fiji", "honshu", "japan", 3776, "n");
        connector.addMountain("everest", "himalayas", "china", 8849, "n");
        connector.addMountain("aconcagua", "andes", "chile", 6961, "s");
        connector.addMountain("ben nevis", "grampian", "scotland", 1345, "n");
    
        System.out.println(connector.getMountainHeight("everest", "himalayas", "china"));
        System.out.println(connector.getMountainHeight("ben nevis", "grampian", "scotland"));
        
        
        
        
        System.out.println(connector.getMountainRxC("himalayas", "china"));
        System.out.println(connector.getMountainC("chile"));
        System.out.println(connector.getMountainHemi("n"));
        
        System.out.println(connector.getMountainHeight(3475));
        connector.upDateMountains("snowden", "snowdonia", "wales", 3475);
        System.out.println(connector.getMountainHeight(3475));
    }
}

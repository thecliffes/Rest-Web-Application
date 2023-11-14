/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.helloresttest;

/**
 *
 * @author bencl
 */
public class Mountain {
    private String name;
    private String mountain;
    private String country;
    private int height;
    private String hemi;
    
    public Mountain(String name, String mountain, String country, int height, String hemi){
        setName(name);
        setMountain(mountain);
        setCountry(country);
        setHeight(height);
        setHemi(hemi);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMountain() {
        return mountain;
    }

    public void setMountain(String mountain) {
        this.mountain = mountain;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHemi() {
        return hemi;
    }

    public void setHemi(String hemi) {
        this.hemi = hemi;
    }
    
    
}

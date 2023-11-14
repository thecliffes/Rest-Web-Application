/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavensingletonrestclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import javax.ws.rs.core.MediaType;




/**
 *
 * @author bencl
 */
public class ResourceConnector {
    private final Client client = Client.create();
    private WebResource baseResource = client.resource("http://localhost:8080/webapi/");
    private final int pathStatus = 200;
    private final int formStatus = 204;
    private final int queryStatus = 200;
    /** 
     * client code for PathParam to get mountain height
     * @param name
     * @param mountain
     * @param country
     * @return mountain height
     */
     public int getMountainHeight(String name, String mountain, String country) {
        WebResource webTarget = baseResource.path("myresource/gHeight")
                .path(name).path(mountain).path(country);
        ClientResponse response = webTarget.type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        if (response.getStatus() != pathStatus) {
            System.out.println("GET Failed with code: " + response.getStatus());
        }
        
        return Integer.parseInt(response.getEntity(String.class));
     }
     
     /**
      * client code for FormParam to add a mountain
      * @param name
      * @param mountain
      * @param country
      * @param height
      * @param hemi 
      */
     public void addMountain(String name, String mountain, String country, int height, String hemi){
        Form form = new Form();
        form.add("name", name);
        form.add("mountain", mountain);
        form.add("country", country);
        form.add("height", height);
        form.add("hemi", hemi);
        WebResource webTarget = baseResource.path("myresource/add");
        ClientResponse response = webTarget
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, form);
        if (response.getStatus() > formStatus) {
            System.out.println("POST Failed with code: " + response.getStatus());
        }
     }
     
     /**
      * client code for PathParam to delete a mountain
      * @param name
      * @param mountain
      * @param country 
      */
     public void deleteMountain(String name, String mountain, String country) {
        WebResource webTarget = baseResource.path("myresource/delete")
                .path(name).path(mountain).path(country);
        ClientResponse response = webTarget.type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        if (response.getStatus() != pathStatus) {
            System.out.println("DELETE Failed with code: " + response.getStatus());
        }
     }
     
     /**
      * client code for FormParam to update a mountain
      * @param name
      * @param mountain
      * @param country
      * @param height 
      */
     public void upDateMountains(String name, String mountain, String country, int height){
        Form form = new Form();
        form.add("name", name);
        form.add("mountain", mountain);
        form.add("country", country);
        form.add("height", height);
        WebResource webTarget = baseResource.path("myresource/update");
        ClientResponse response = webTarget
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, form);
        if (response.getStatus() > formStatus) {
            System.out.println("PUT Failed with code: " + response.getStatus());
        }
     }
     
     /**
      * client code for QueryParam to get mountain range and country
      * @param mountain
      * @param country
      * @return 
      */
     public String getMountainRxC(String mountain, String country){
         WebResource webTarget = baseResource.path("myresource/getRxC");
        ClientResponse response = webTarget
                .queryParam("mountain", mountain)
                .queryParam("country", country).type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        if (response.getStatus() != queryStatus) {
            System.out.println("GET Failed with code: " + response.getStatus());
        }
        
        return response.getEntity(String.class);
     }
     
     /**
      * client code for QueryParam to get country 
      * @param country
      * @return 
      */
     public String getMountainC(String country){
         WebResource webTarget = baseResource.path("myresource/getC");
        ClientResponse response = webTarget
                .queryParam("country", country).type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        if (response.getStatus() != queryStatus) {
            System.out.println("GET Failed with code: " + response.getStatus());
        }
        
        return response.getEntity(String.class);
     }

     /**
      * client code for QueryParam to get mountain hemisphere 
      * @param hemi
      * @return 
      */
     public String getMountainHemi(String hemi){
         WebResource webTarget = baseResource.path("myresource/getHemi");
        ClientResponse response = webTarget
                .queryParam("hemi", hemi).type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        if (response.getStatus() != queryStatus) {
            System.out.println("GET Failed with code: " + response.getStatus());
        }
        
        return response.getEntity(String.class);
     }
     
     /**
      * client code for QueryParam to get mountains over a specified height
      * @param height
      * @return 
      */
     public String getMountainHeight(int height){
         WebResource webTarget = baseResource.path("myresource/getHeight");
        ClientResponse response = webTarget
                .queryParam("height", Integer.toString(height)).type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        if (response.getStatus() != queryStatus) {
            System.out.println("GET Failed with code: " + response.getStatus());
        }
        
        return response.getEntity(String.class);
     }

}

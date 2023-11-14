package com.mycompany.helloresttest;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Root resource (exposed at "myresource" path)
 */
@Singleton
@Path("myresource")
public class MyResource {

    private String message;
    private ArrayList<Mountain> mountains = new ArrayList<Mountain>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Adds new mountain into data base using POST
     *
     * @param name
     * @param mountain
     * @param country
     * @param height
     * @param hemi
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addMountain(@FormParam("name") String name,
            @FormParam("mountain") String mountain,
            @FormParam("country") String country,
            @FormParam("height") int height,
            @FormParam("hemi") String hemi) {
        lock.writeLock().lock();
        try {
            Mountain m = new Mountain(name, mountain, country, height, hemi);
            boolean exists = false;

            //Checks there is no other mountain with the same name, country and range in the list before adding it
            for (Mountain list : mountains) {
                if (list.getName().equals(name) && list.getMountain().equals(mountain) && list.getCountry().equals(country)) {
                    exists = true;
                }
            }

            if (!exists) {
                mountains.add(m);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Removes a mountain from data base using DELETE
     *
     * @param name
     * @param mountain
     * @param country
     */
    @DELETE
    @Path("/delete/{name}/{mountain}/{country}")
    public void deleteMountain(@PathParam("name") String name,
            @PathParam("mountain") String mountain,
            @PathParam("country") String country) {
        lock.writeLock().lock();
        try {
            for (Mountain m : mountains) {
                if (m.getName().equals(name) && m.getMountain().equals(mountain) && m.getCountry().equals(country)) {
                    mountains.remove(m);
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * update height on a mountain using POST
     *
     * @param name
     * @param mountain
     * @param country
     * @param height
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void upDateMountains(@FormParam("name") String name,
            @FormParam("mountain") String mountain,
            @FormParam("country") String country,
            @FormParam("height") int height) {
        lock.writeLock().lock();
        try {
            for (Mountain m : mountains) {
                if (!mountains.isEmpty() && m.getName().equals(name)
                        && m.getMountain().equals(mountain)
                        && m.getCountry().equals(country)) {
                    m.setHeight(height);

                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Return height of named mountain using GET
     *
     * @param name
     * @param mountain
     * @param country
     * @return
     */
    @GET
    @Path("/gHeight/{name}/{mountain}/{country}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getMountainHeight(@PathParam("name") String name,
            @PathParam("mountain") String mountain,
            @PathParam("country") String country) {
        lock.readLock().lock();
        try {
            int h = 0;
            for (Mountain m : mountains) {
                if (m.getName().equals(name) && m.getMountain().equals(mountain) && m.getCountry().equals(country)) {
                    h = m.getHeight();
                }
            }
            return h;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Get all mountains in specified params using GET
     *
     * @param mountain
     * @param country
     * @return
     */
    @GET
    @Path("/getRxC")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMountainRxC(@QueryParam("mountain") String mountain,
            @QueryParam("country") String country) {
        lock.readLock().lock();
        try {
            ArrayList<String> mountainList = new ArrayList<String>();
            String list = null;
            for (Mountain m : mountains) {
                if (m.getMountain().equals(mountain) && m.getCountry().equals(country)) {
                    mountainList.add(m.getName() + m.getHeight());
                }
            }
            list = String.join(", ", mountainList);
            return list;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Get all mountains in specified params using GET
     *
     * @param country
     * @return
     */
    @GET
    @Path("/getC")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMountainC(@QueryParam("country") String country) {
        lock.readLock().lock();
        try {
            ArrayList<String> mountainList = new ArrayList<String>();
            String list = null;
            for (Mountain m : mountains) {
                if (m.getCountry().equals(country)) {
                    mountainList.add(m.getName() + m.getHeight());
                }
            }
            list = String.join(", ", mountainList);
            return list;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Get all mountains in specified params using GET
     *
     * @param hemi
     * @return
     */
    @GET
    @Path("/getHemi")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMountainHemi(@QueryParam("hemi") String hemi) {
        lock.readLock().lock();
        try {
            ArrayList<String> mountainList = new ArrayList<String>();
            String list = null;
            for (Mountain m : mountains) {
                if (m.getHemi().equals(hemi)) {
                    mountainList.add(m.getName() + m.getHeight());
                }
            }
            list = String.join(", ", mountainList);
            return list;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Gets all mountains over a specified height using GET
     *
     * @param height
     * @return
     */
    @GET
    @Path("/getHeight")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMountainHeight(@QueryParam("height") int height) {
        lock.readLock().lock();
        try {
            ArrayList<String> mountainList = new ArrayList<String>();
            String list = null;
            for (Mountain m : mountains) {
                if (m.getHeight() >= height) {
                    mountainList.add(m.getName() + m.getHeight());
                }
            }
            list = String.join(", ", mountainList);
            return list;
        } finally {
            lock.readLock().unlock();
        }
    }

}

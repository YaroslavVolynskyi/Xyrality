package com.xyrality.gmbh.app;

import org.json.simple.JSONObject;

/**
 * Created by yarik on 26.09.15.
 */
public class GameWorld {

    private String country;
    private int id;
    private String language;
    private String mapUrl;
    private String url;
    private String name;
    private WorldStatus worldStatus;

    private static final String WORLDS_KEY = "allAvailableWorlds";

    public static String getWorldsKey() {
        return WORLDS_KEY;
    }

    public GameWorld(JSONObject worldJSON) {
        if (worldJSON.get("name") != null) {
            setName((String) worldJSON.get("name"));
        }
        if (worldJSON.get("country") != null) {
            setCountry((String) worldJSON.get("country"));
        }
        if (worldJSON.get("language") != null) {
            setLanguage((String) worldJSON.get("language"));
        }
        if (worldJSON.get("id") != null) {
            setId(Integer.parseInt((String) worldJSON.get("id")));
        }
        if (worldJSON.get("mapURL") != null) {
            setMapUrl((String) worldJSON.get("mapURL"));
        }
        if (worldJSON.get("url") != null) {
            setUrl((String) worldJSON.get("url"));
        }
        if (worldJSON.get("worldStatus") != null) {
            JSONObject statusJSON = (JSONObject) worldJSON.get("worldStatus");
            WorldStatus status = new WorldStatus();
            if (statusJSON.get("id") != null) {
                status.setId((long) statusJSON.get("id"));
            }
            if (statusJSON.get("description") != null) {
                status.setDescription((String) statusJSON.get("description"));
            }
            setWorldStatus(status);
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorldStatus getWorldStatus() {
        return worldStatus;
    }

    public void setWorldStatus(WorldStatus worldStatus) {
        this.worldStatus = worldStatus;
    }
}


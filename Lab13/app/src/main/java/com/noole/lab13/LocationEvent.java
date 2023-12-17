package com.noole.lab13;

import com.noole.lab13.helper.Location;

public class LocationEvent {

    private Location location;

    public LocationEvent(Location locationUpdate) {
        this.location = locationUpdate;
    }

    public Location getLocation() {
        return location;
    }
}

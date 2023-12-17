package com.noole.lab13.helper;

import android.content.Context;
import android.os.Build;
import pub.devrel.easypermissions.EasyPermissions;

public class Utils {

    public static final Utils INSTANCE;

    private Utils(){

    }
    static {
        INSTANCE = new Utils();
    }

    public final boolean locationPermissions(Context context){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.Q ?
                EasyPermissions.hasPermissions(context,"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_COARSE_LOCATION" ) :
                EasyPermissions.hasPermissions(context,"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_COARSE_LOCATION") ;
    }
}

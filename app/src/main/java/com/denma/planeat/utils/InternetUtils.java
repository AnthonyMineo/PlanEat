package com.denma.planeat.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetUtils {

    public static Boolean isInternetAvailable(Context context){
        Boolean connectivity = false;
        if(isWifiAvailable(context) || isMobileDataAvailable(context)){
            connectivity = true;
        }
        return connectivity;
    }

    public static Boolean isWifiAvailable(Context context){
        Boolean wifi = false;
        ConnectivityManager cM = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cM.getActiveNetworkInfo();
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
            wifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return wifi;
    }

    public static Boolean isMobileDataAvailable(Context context){
        Boolean data = false;
        ConnectivityManager cM = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cM.getActiveNetworkInfo();
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
            data = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return data;
    }
}

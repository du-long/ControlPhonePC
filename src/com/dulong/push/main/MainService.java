package com.dulong.push.main;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MainService {
    //    http://192.168.0.44:8081/control/send?ipAddress=192.168.0.44&registrationID=160a3797c83e228f768
    @GET()
    Observable<String> video(@Url String baseUrl, @Query("ipAddress") String ipAddress, @Query("registrationID") String registrationID);

    //    http://192.168.0.44:8081/control/send?ipAddress=192.168.0.44&registrationID=160a3797c83e228f768
    @GET()
    Observable<String> online(@Url String baseUrl, @Query("ipAddress") String ipAddress, @Query("type") String type);
}

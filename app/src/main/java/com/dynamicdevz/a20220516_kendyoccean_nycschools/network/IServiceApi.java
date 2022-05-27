package com.dynamicdevz.a20220516_kendyoccean_nycschools.network;

import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Schools;
import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Scores;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface IServiceApi {

    String BASE_URL = "https://data.cityofnewyork.us/resource/";

    @GET("s3k6-pzi2")
    Single<List<Schools>> getAllSchools();

    @GET("f9bf-2cp4")
    Single<List<Scores>> getSchoolsScore();
}

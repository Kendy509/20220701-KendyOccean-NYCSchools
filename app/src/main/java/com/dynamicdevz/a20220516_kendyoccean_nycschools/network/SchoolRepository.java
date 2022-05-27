package com.dynamicdevz.a20220516_kendyoccean_nycschools.network;

import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Schools;
import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Scores;

import java.util.List;
import io.reactivex.Single;

public interface SchoolRepository {
    Single<List<Schools>> getSchools();
    Single<List<Scores>> getScores(String schoolDbn);
}

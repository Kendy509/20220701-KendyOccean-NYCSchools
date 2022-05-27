package com.dynamicdevz.a20220516_kendyoccean_nycschools.network;

import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Schools;
import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Scores;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class SchoolRepositoryImpl implements SchoolRepository {

    private final IServiceApi serviceApi;

    @Inject
    public SchoolRepositoryImpl(IServiceApi apiService) {
        serviceApi = apiService;
    }

    @Override
    public Single<List<Schools>> getSchools() {
        return serviceApi.getAllSchools();
    }

    @Override
    public Single<List<Scores>> getScores(String schoolDbn) {
        return serviceApi.getSchoolsScore()
                .map(scores ->
                        scores.stream().filter(scores1 ->
                                Objects.equals(scores1.getDbn(), schoolDbn)).collect(Collectors.toList()));
    }
}

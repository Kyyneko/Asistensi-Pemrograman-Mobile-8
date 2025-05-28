package com.example.tp6;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/character")
    Call<EntityResponse> getEntities(@Query("page") int page);

    @GET("api/character/{id}")
    Call<Entity> getCharacterDetail(@Path("id") int id);

}

package dita.shafira.mate.service;

import java.util.List;

import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.Race;
import dita.shafira.mate.model.ResponseCat;
import dita.shafira.mate.model.ResponseLogin;
import dita.shafira.mate.model.ResponseRace;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("cat/race")
    Call<List<Race>> getRace();

    @GET("cat/me/{user_id}")
    Call<List<Cat>> getMyCat(@Path("user_id") String user_id);

    @GET("cat/me/{user_id}/{cat_id}")
    Call<Cat> getMyCatDetail(@Path("user_id") String user_id,@Path("cat_id") String cat_id);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> login(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseLogin> register(
            @Field("name") String usernmae,
            @Field("email") String email,
            @Field("address") String location,
            @Field("password") String password);



}

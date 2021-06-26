package dita.shafira.mate.service;

import java.util.List;

import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.CatSearch;
import dita.shafira.mate.model.Mating;
import dita.shafira.mate.model.Race;
import dita.shafira.mate.model.Response;
import dita.shafira.mate.model.ResponseLogin;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    //Auth
    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseLogin> register(
            @Field("name") String usernmae,
            @Field("email") String email,
            @Field("address") String location,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("update/profile")
    Call<ResponseLogin> updateProfile(
            @Field("id") String user_id,
            @Field("name") String usernmae,
            @Field("address") String location,
            @Field("password") String password);

    @Multipart
    @POST("update/profile/photo")
    Call<ResponseLogin> updateProfilePhoto(
            @Part("id") String user_id,
            @Part MultipartBody.Part image
    );
//    @Multipart
//    @POST("cat/update/photo")
//    Call<Response> catUpdatePhoto(
//            @Part("cat_id") String catId,
//            @Part MultipartBody.Part image
//    );

    @FormUrlEncoded
    @POST("update/profile/status")
    Call<Response> userUpdateStatus(
            @Field("id") String id,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("check/login")
    Call<Response> checkLogin(@Field("token") String token);

    @FormUrlEncoded
    @POST("check/login")
    Call<Response> logout(@Field("token") String token);

    @FormUrlEncoded
    @POST("update/location")
    Call<Response> updateLocation(
            @Field("id") String id,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude
    );


    @GET("cat/race")
    Call<List<Race>> catRace();

    @GET("cat/me/{user_id}/{cat_id}")
    Call<Cat> catMeDetail(@Path("user_id") String user_id, @Path("cat_id") String cat_id);

    @GET("cat/me/{user_id}")
    Call<List<Cat>> catMe(@Path("user_id") String user_id);

    @Multipart
    @POST("cat/store")
    Call<Response> catStore(
            @Part("name") String name,
            @Part("race_id") int race,
            @Part("birth") String birth,
            @Part("sex") int sex,
            @Part("user_id") int user,
            @Part MultipartBody.Part image
    );

    @FormUrlEncoded
    @POST("cat/update")
    Call<Response> catUpdate(
            @Field("cat_id") String catId,
            @Field("name") String name,
            @Field("last_parasite") String lastParasite,
            @Field("last_vaccine") String lastVaccine
    );

    @FormUrlEncoded
    @POST("cat/update/status")
    Call<Response> catUpdateStatus(
            @Field("cat_id") String catId,
            @Field("status") String status
    );

    @Multipart
    @POST("cat/update/photo")
    Call<Response> catUpdatePhoto(
            @Part("cat_id") String catId,
            @Part MultipartBody.Part image
    );

    @FormUrlEncoded
    @POST("cat/remove/photo")
    Call<Response> catRemovePhoto(
            @Field("path") String path
    );

    @Multipart
    @POST("cat/add/photo")
    Call<Response> catAddPhoto(
            @Part MultipartBody.Part image
    );

    @GET("cat/me/love/{cat_id}")
    Call<Mating> catLove(@Path("cat_id") String cat_id);

    @GET("cat/me/married/{cat_id}")
    Call<List<Mating>> catMarried(@Path("cat_id") String cat_id);

    @FormUrlEncoded
    @POST("cat/me/search")
    Call<List<CatSearch>> catSearch(
            @Field("user_id") int userId,
            @Field("sex") int sex,
            @Field("race") int race,
            @Field("distance") int distance
    );

    @FormUrlEncoded
    @POST("cat/me/mating")
    Call<Response> catMating(
            @Field("cat_id_1") int cat1,
            @Field("cat_id_2") int cat2,
            @Field("status_mate") int status_mate,
            @Field("status_chat") int status_chat
    );

    @FormUrlEncoded
    @POST("cat/me/mating/changing")
    Call<Response> catMatingChanging(
            @Field("mating_id") int mating,
            @Field("status") int status
    );

    @GET("chat/{user}")
    Call<List<Mating>> getListChat(@Path("user") String user);

    @FormUrlEncoded
    @POST("chat/last-chat")
    Call<Response> lastChat(
            @Field("id") int id,
            @Field("last_chat") String s,
            @Field("sender") int userId
    );
    @FormUrlEncoded
    @POST("chat/status-mate")
    Call<Response> statusMate(
            @Field("id") int id,
            @Field("status_mate") int sm
    );
    @FormUrlEncoded
    @POST("chat/status-chat")
    Call<Response> statusChat(
            @Field("id") int id,
            @Field("status_chat") int sc
    );
    @FormUrlEncoded
    @POST("chat/user-read")
    Call<Response> userRead(
            @Field("id") int id,
            @Field("reader") int userId
    );
    @FormUrlEncoded
    @POST("cat/user-read-2")
    Call<Response> userRead2(
            @Field("id") int id
    );



}

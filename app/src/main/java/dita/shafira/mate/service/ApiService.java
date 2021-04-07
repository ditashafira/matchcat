package dita.shafira.mate.service;

import java.util.List;

import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.ResponseCat;
import dita.shafira.mate.model.ResponseRace;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("cat/race")
    Call<ResponseRace> getRace();
    @GET("cat/me/{user_id}")
    Call<List<Cat>> getMyCat(@Path("user_id") String user_id);
    @GET("cat/me/{user_id}/{cat_id}")
    Call<Cat> getMyCatDetail(@Path("user_id") String user_id,@Path("cat_id") String cat_id);

}

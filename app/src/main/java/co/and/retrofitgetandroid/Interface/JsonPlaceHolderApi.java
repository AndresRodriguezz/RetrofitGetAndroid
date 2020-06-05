package co.and.retrofitgetandroid.Interface;

import java.util.List;

import co.and.retrofitgetandroid.Model.Posts;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Posts>> getPost ();
}

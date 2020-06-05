package co.and.retrofitgetandroid;

import androidx.appcompat.app.AppCompatActivity;
import co.and.retrofitgetandroid.Interface.JsonPlaceHolderApi;
import co.and.retrofitgetandroid.Model.Posts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsonTxtView = (TextView)findViewById(R.id.jsonText);
        getPosts();

    }
    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Posts>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }
                List<Posts> postsList = response.body();
                for(Posts post: postsList){
                    String content ="";
                    content += "userId" + post.getUserId()+"\n";
                    content += "id:" + post.getId() + "\n";
                    content += "title" + post.getTitle() + "\n";
                    content += "body"+ post.getBody() + "\n";
                    mJsonTxtView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

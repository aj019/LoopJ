package com.mate.android.loopj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etSearch;
    Button btSearch;
    TextView tvResponse;
    AsyncHttpClient client = new AsyncHttpClient();
    RequestParams requestParams = new RequestParams();
    String BASE_URL = "http://www.omdbapi.com/?";
    String jsonResponse;
    String TAG = "MOVIE_TRIVIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = (EditText) findViewById(R.id.etSearchTerms);
        btSearch = (Button) findViewById(R.id.btnSearch);
        tvResponse = (TextView) findViewById(R.id.tvSearchResults) ;

        btSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String searchText = etSearch.getText().toString();
        etSearch.setText("");
        executeLoopJ(searchText);

    }


    public void executeLoopJ(String s){

        requestParams.put("x",s);

        client.get(BASE_URL,requestParams,new JsonHttpResponseHandler(){



            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                jsonResponse = response.toString();
                Log.i(TAG, "onSuccess: " + jsonResponse);
                tvResponse.setText(jsonResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e(TAG, "onFailure: " + errorResponse);
                tvResponse.setText("Some error has occured");
            }

            public void onFinish(){
                Log.i(TAG, "Finished: ");
            }

        });


    }

}

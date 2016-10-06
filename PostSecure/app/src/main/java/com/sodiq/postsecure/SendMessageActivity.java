package com.sodiq.postsecure;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sodiq.postsecure.preferences.DataPreferences;
import com.sodiq.postsecure.utils.Aes;
import com.sodiq.postsecure.utils.rest.RestClient;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class SendMessageActivity extends AppCompatActivity {
    @Bind(R.id.etMessage)
    EditText etMessage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.btnSend)
    public void send(View view) {
        String message = etMessage.getText().toString();
        if (message.isEmpty()) {
            etMessage.setText("message is required");
        } else {
            sendMessage(message);
        }
    }

    private void sendMessage(String message) {
        Aes aes = new Aes();
        String message_secure;
        try {
            message_secure = aes.encrypt(Constant.AES_KEY, message, Constant.AES_TYPE);
        } catch (Exception e) {
            message_secure = "";
        }
        DataPreferences dataPreferences = new DataPreferences(SendMessageActivity.this);
        int user_id = dataPreferences.getUserId();
        String token = dataPreferences.getToken();
        RequestParams params = new RequestParams();
        params.put("message", message_secure);
        params.put("user_id", user_id);
        params.put("token", token);
        Log.e("params", params.toString());
        RestClient.post("send_message.php", params,
                new JsonHttpResponseHandler() {
                    ProgressDialog progressView;

                    @Override
                    public void onStart() {
                        super.onStart();
                        progressView = new ProgressDialog(SendMessageActivity.this);
                        progressView.setIndeterminate(true);
                        progressView.setCancelable(false);
                        progressView.setMessage("Please Wait");
                        progressView.show();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        progressView.dismiss();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Toast.makeText(getApplicationContext(),
                                "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                       Toast.makeText(getApplicationContext(),
                                "something error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;


public class ComposeActivity extends ActionBarActivity {

    private static final int STATUS_MAX_LENGTH = 140;
    private EditText etStatus;
    private TextView tvCharCount;
    private TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client = TwitterApplication.getRestClient();

        etStatus = (EditText) findViewById(R.id.etStatus);
        tvCharCount = (TextView) findViewById(R.id.tvCharCount);

        tvCharCount.setText(String.valueOf(STATUS_MAX_LENGTH));
        tvCharCount.setTextColor(Color.GRAY);

        etStatus.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = STATUS_MAX_LENGTH - s.length();
                tvCharCount.setText(String.valueOf(count));
                if (count > 20) {
                    tvCharCount.setTextColor(Color.rgb(51, 181, 229)); // blue
                } else if (count > 10) {
                    tvCharCount.setTextColor(Color.rgb(153, 51, 204)); // violet
                } else if (count > 5) {
                    tvCharCount.setTextColor(Color.rgb(255, 136, 0)); // orange
                } else {
                    tvCharCount.setTextColor(Color.rgb(204, 0, 0)); // red
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_compose, menu);
        return true;
    }





    public void onCancel(MenuItem mi) {
        Intent i = new Intent();
        setResult(RESULT_CANCELED, i);
        finish();
    }

    public void onSend(MenuItem mi) {
        String composedTweet;
        etStatus = (EditText) findViewById(R.id.etStatus);
        composedTweet = etStatus.getText().toString();
        client.postUpdate(composedTweet, new JsonHttpResponseHandler() {

        });


        Intent i = new Intent(this, TimelineActivity.class);
        i.putExtra("status", etStatus.getText().toString());
        setResult(RESULT_OK, i);
        finish();
        startActivity(i);
    }
}

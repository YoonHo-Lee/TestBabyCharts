package com.example.tacademy.testbabycharts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText keywordView;
    ListView listView;
    BabyAdapter mAdapter;
    private static final boolean isAbuBabies = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keywordView = (EditText) findViewById(R.id.edit_keyword);

        Button btn = (Button) findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovie("test02");
            }
        });



        listView = (ListView) findViewById(R.id.listView);


        if (isAbuBabies) {
            mAdapter = new BabyAdapter();
            listView.setAdapter(mAdapter);
            keywordView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    searchMovie(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

    }



    private void searchMovie(final String id) {
        if (!TextUtils.isEmpty(id)) {
            NetworkManager.getInstance().getNetworkMovie(this, id, new NetworkManager.OnResultListener<AbuBabies>() {

                @Override
                public void onSuccess(AbuBabies result) {
                    mAdapter.clear();
                    for(BabyItem item : result.result)   {
                        mAdapter.add(item);
                    }
                }

                @Override
                public void onFail(int code) {
                    Toast.makeText(MainActivity.this, "error : " + code, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mAdapter.clear();
            mAdapter.setKeyword(id);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getInstance().cancelAll(this);
    }


}

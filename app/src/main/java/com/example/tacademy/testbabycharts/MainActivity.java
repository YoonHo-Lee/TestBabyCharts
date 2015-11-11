package com.example.tacademy.testbabycharts;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity {

    EditText keywordView;
    ListView listView;
    SwipeRefreshLayout refreshLayout;
    BabyAdapter mAdapter;
    private static final boolean isNaverMovie = true;
    private static final boolean isRefreshLibrary = false;

    boolean isUpdate = false;

    boolean isLastItem = false;


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


        if (isNaverMovie) {
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

//    private void getMoreItem() {
//        if (!isUpdate) {
//            String keyword = mAdapter.getKeyword();
//            int startIndex = mAdapter.getStartIndex();
//            if (!TextUtils.isEmpty(keyword) && startIndex != -1) {
//                isUpdate = true;
//                NetworkManager.getInstance().getNetworkMovie(MainActivity.this, keyword, startIndex, 10, new NetworkManager.OnResultListener<AbuBabies>() {
//                    @Override
//                    public void onSuccess(AbuBabies result) {
//                        for (BabyItem item : result.items) {
//                            mAdapter.add(item);
//                        }
//                        isUpdate = false;
//                    }
//
//                    @Override
//                    public void onFail(int code) {
//                        isUpdate = false;
//                    }
//                });
//            }
//        }
//    }

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

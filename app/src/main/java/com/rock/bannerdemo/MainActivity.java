package com.rock.bannerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rock.bannerdemo.adapter.BannerAdapter;
import com.rock.bannerdemo.entity.EntityBean;
import com.rock.bannerdemo.view.BannerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private BannerView mBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBannerView = (BannerView) findViewById(R.id.bannerView);
        initDate();
    }

    private void initDate() {
        OkHttpUtils
                .get()
                .url("http://gank.io/api/data/福利/10/1")
                .addParams("page", "1")
                .build()
                .execute(new StringCallback()
                {

                    private List<EntityBean> resultsBeen;

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            resultsBeen = new ArrayList<EntityBean>();
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.optJSONArray("results");
                            for (int i = 0 ;i< jsonArray.length(); i++){
                                JSONObject o = jsonArray .optJSONObject(i);
                                EntityBean bean= new EntityBean();
                                bean.title = o.optString("desc");
                                bean.picUrl = o.optString("url");
                                resultsBeen.add(bean);
                            }
                            mBannerView.setAdapter(new BannerAdapter() {
                                @Override
                                public View getView(int position, View convertView) {
                                    ImageView bannerIv;
                                    if (convertView == null) {
                                        bannerIv = new ImageView(MainActivity.this);
                                        bannerIv.setScaleType(ImageView.ScaleType.FIT_XY);
                                    } else {
                                        bannerIv = ( ImageView ) convertView;
                                        Log.i("TAG", "getView: 界面复用" + bannerIv);
                                    }
                                    if (position == 0) {
                                        Glide.with(MainActivity.this).load(resultsBeen.get(0).picUrl).into(bannerIv);
                                    } else {
                                        Glide.with(MainActivity.this).load(resultsBeen.get(position).picUrl).into(bannerIv);
                                    }
                                    return bannerIv;
                                }

                                @Override
                                public int getCount() {
                                    return resultsBeen.size();
                                }
                            });
                            mBannerView.startLoop();
                            mBannerView.setScrollerDuration(1000);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

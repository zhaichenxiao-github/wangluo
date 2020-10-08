package com.example.wangluo.fragment;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.wangluo.GalleyTransFormer;
import com.example.wangluo.MainActivity;
import com.example.wangluo.R;
import com.example.wangluo.adapter.DisCountAdapter;
import com.example.wangluo.app.ShopApplication;
import com.example.wangluo.bean.DiscountBean;
import com.example.wangluo.utils.BannerImageLoad;
import com.paradoxie.autoscrolltextview.VerticalTextview;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv)
    ImageView iv;
    private int REQUEST_CODE=1;

    private final String BANNER_ONE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876108368&di=cd9725c81901f6d7499edd76cf2e68e5&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F37%2F20%2F80Q58PICe3W_1024.jpg";
    private final String BANNER_TWO = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502882008108&di=d0cf4a8536aefa5df791716c1053ca66&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01e9495812c7f9a84a0d304fbc135b.jpg";
    private final String BANNER_THREE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876281925&di=f33e7ef8be268e90ffbffd315f5fb0a3&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013e1b57d2731c0000018c1beeca11.jpg%40900w_1l_2o_100sh.jpg";
    private final String BANNER_FOUR = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=c&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg";

    //折扣图片
    private final String ZHEKOU_ONE="https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg";
    private final String ZHEKOU_TWO="https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg";
    private final String ZHEKOU_THREE="https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg";
    private final String ZHEKOU_FOUR="https://img11.360buyimg.com/n7/jfs/t4447/301/1238553109/193354/13c7e995/58db19a7N25101fe4.jpg";
    private final String ZHEKOU_FIVE="https://img14.360buyimg.com/n1/s190x190_jfs/t7525/189/155179632/395056/e200017f/598fb8a4N7800dee6.jpg";

    //话题
    private final String HUATI_ONE="http://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg";
    @BindView(R.id.iv_ss)
    ImageView ivSs;
    private ImageView ivSys;
    private Banner banner;
    private ArrayList<String> paoma;
    private VerticalTextview vt;
    private TextView tvrv;
    private ArrayList<DiscountBean> list;
    private DisCountAdapter disCountAdapter;
    private RecyclerView rv;
    private ViewPager rl_vp;

    private String[] arr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initListener();
        initPressmiser();
        return view;
    }

    private void initPressmiser() {
        String [] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.VIBRATE,
        };
        ActivityCompat.requestPermissions(getActivity(),permissions,100);
    }

    private void initListener() {

    }

    private void initView(View view) {
        rl_vp = view.findViewById(R.id.rl_vp);
        rv = view.findViewById(R.id.rv);
        tvrv = view.findViewById(R.id.tv_rv);
        vt = view.findViewById(R.id.vt);
        banner = view.findViewById(R.id.banner);
        ivSys = view.findViewById(R.id.iv_sys);
        ArrayList<String> bannerimages = new ArrayList<>();
        bannerimages.add(BANNER_ONE);
        bannerimages.add(BANNER_TWO);
        bannerimages.add(BANNER_THREE);
        bannerimages.add(BANNER_FOUR);
        banner.setImageLoader(new BannerImageLoad()).setImages(bannerimages).setBannerStyle(BannerConfig.NUM_INDICATOR).setBannerAnimation(Transformer.Accordion).start();

        paoma = new ArrayList<>();
        paoma.add("夏日炎炎，第一波福利还有30秒到达战场");
        paoma.add("新用户立领1000元优惠券");
        vt.setTextList(paoma);//加入显示内容,集合类型
        vt.setText(8, 0, Color.RED);//设置属性,具体跟踪源码
        vt.setTextStillTime(3000);//设置停留时长间隔
        vt.setAnimTime(300);//设置进入和退出的时间间隔
        //对单挑文字进行监听
        vt.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {

            }
        });
        tvrv.setText("火爆进行中");
        TextPaint paint = tvrv.getPaint();
        paint.setFakeBoldText(true);
        list = new ArrayList<>();
        list.add(new DiscountBean(ZHEKOU_ONE,"￥123.00","$1000.00"));
        list.add(new DiscountBean(ZHEKOU_TWO,"￥123.00","$1000.00"));
        list.add(new DiscountBean(ZHEKOU_THREE,"￥123.00","$1000.00"));
        list.add(new DiscountBean(ZHEKOU_FOUR,"￥123.00","$1000.00"));
        list.add(new DiscountBean(ZHEKOU_FIVE,"￥123.00","$1000.00"));
        rv.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        disCountAdapter = new DisCountAdapter(getActivity(),list);
        rv.setAdapter(disCountAdapter);
        disCountAdapter.notifyDataSetChanged();


        //画廊


        rl_vp.setPageTransformer(true, new GalleyTransFormer());
        rl_vp.setOffscreenPageLimit(5);
        rl_vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//                ImageView imageView = new ImageView(getActivity());
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setImageResource(Integer.parseInt(arr[0]));
//                container.addView(imageView);
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_hua, null);
                ImageView iv = view1.findViewById(R.id.iv);
                TextView tv_title = view1.findViewById(R.id.tv_title);
                TextView tv_desc = view1.findViewById(R.id.tv_desc);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setImageResource(R.mipmap.c);
                tv_desc.setText("爱生活");
                tv_title.setText("爱生活");
                container.addView(view1);
                return view1;
            }
        });
        rl_vp.setCurrentItem(5);
        /*设置点击ViewPager之外的部位，ViewPager跟着滑动*/
        RelativeLayout parent = (RelativeLayout) rl_vp.getParent();
        parent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return rl_vp.dispatchTouchEvent(event);
            }
        });
        //扫一扫
        ivSys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE&&resultCode == RESULT_OK){
            String result = data.getExtras().getString("result");
            Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        vt.startAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        vt.stopAutoScroll();
    }
}

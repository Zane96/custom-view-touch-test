package com.example.think.testview;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;



/**
 * Created by Zane on 15/11/22.
 * 事件反窃取的练习,当你点击了BUTTO，然后你在button上面滑动，阻止SCROLLVIEW窃取MOVE事件
 */
public class TouchInterceptActivity extends AppCompatActivity{

    private ScrollView mScrollview;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytouchntercept_layout);

        mScrollview = (ScrollView) findViewById(R.id.scrollview2);
        mButton = (Button) findViewById(R.id.button_scrollview);

        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        mScrollview.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TouchInterceptActivity.this, "click", Toast.LENGTH_LONG).show();
            }
        });
    }

//        private ViewPager mViewpager;
//    private ListView mListview;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mytouchntercept_layout);
//
//        mListview = (ListView) findViewById(R.id.listview_touchinterceptactivity);
//        //初始化一个viewpager作为listview的headview
//        mViewpager = new ViewPager(this);
//        mViewpager.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, 400));
//        mListview.addHeaderView(mViewpager);
//
//        mListview.setAdapter(new MyListviewAdapter(this));
//        mViewpager.addOnPageChangeListener(this);
//        mViewpager.setAdapter(new HeaderAdapter(this));
//
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//
//    /**
//     * 如果viewpager是在滑动，那么应该告诉父view(listview不应该取窃取move事件)
//     * @param state
//     */
//    @Override
//    public void onPageScrollStateChanged(int state) {
//        //三种STAT，自己看源码
//        boolean isScroing = state != ViewPager.SCROLL_STATE_IDLE;
//        if (state != ViewPager.SCROLL_STATE_IDLE){
//            mListview.requestDisallowInterceptTouchEvent(false);
//        }
//
//        Toast.makeText(this, String.valueOf(isScroing), Toast.LENGTH_SHORT).show();
//
//    }
//
//    private class MyListviewAdapter extends BaseAdapter {
//
//        private LayoutInflater mInflater;
//
//        public MyListviewAdapter(Context context) {
//            mInflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            return 50;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = mInflater.inflate(R.layout.item_listview_layout, parent, false);
//            }
//
//            TextView v = (TextView) convertView.findViewById(R.id.text);
//            v.setText(String.format("第 %d 行", position + 1));
//
//            v.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_MOVE:
//                            mListview.requestDisallowInterceptTouchEvent(true);
//                    }
//                    return false;
//                }
//            });
//
//            return convertView;
//        }
//    }
//    private static class HeaderAdapter extends PagerAdapter {
//        private static final int[] COLORS = {0xFF555500, 0xFF770077, 0xFF007777, 0xFF777777};
//
//        private Context mContext;
//
//        public HeaderAdapter(Context context) {
//            mContext = context;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            TextView v = new TextView(mContext);
//            v.setBackgroundColor(COLORS[position]);
//            v.setText(String.format("第 %d 张page", position + 1));
//            v.setGravity(Gravity.CENTER);
//            container.addView(v);
//
//            return v;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//
//        @Override
//        public int getCount() {
//            return COLORS.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return (view == object);
//        }
//    }
}

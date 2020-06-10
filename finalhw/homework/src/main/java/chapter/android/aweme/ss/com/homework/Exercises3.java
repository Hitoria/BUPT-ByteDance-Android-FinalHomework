package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;



import java.util.ArrayList;
import java.util.List;


public class Exercises3 extends AppCompatActivity {


    private MyAdapter mAdapter;
    private RecyclerView mList;
    private List<String> datas;
    private VideoView videoView;

    private String[] describes = {"nickname:王火火\ndescription:这是第一条Feed数据\nlikecount:10000",
                                    "nickname:LILILI\ndescription:这是一条一起学猫叫的视频\nlikecount:120000",
                                    "nickname:新闻启示录\ndescription:赶紧把这个转发给你们的女朋友吧，这才是对她们最负责的AI\nlikecount:45000000",
                                    "nickname:综艺大咖秀\ndescription:男明星身高暴击\nlikecount:98777000",
                                    "nickname:南翔不爱吃饭\ndescription:挑战手抓饼的一百种吃法第七天\nlikecount:500000",
                                    "nickname:王者主播那些事儿\ndescription:你有试过蔡文姬打野吗？\nlikecount:1000000",
                                    "nickname:十秒学做菜\ndescription:两款爱吃的三明治分享\nlikecount:1010102",
                                    "nickname:九零后老母亲\ndescription:从孕期到产后，老公一直要我用这个勺子喝汤\nlikecount:94321",
                                    "nickname:FPX电子竞技俱乐部\ndescription:甲方的需求：F P X冠军皮肤的起源\nlikecount:1200000",
                                    "nickname:抖音官方广告报名！\ndescription:买它！买它！买它！\nlikecount:480"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);


        mList = (RecyclerView) findViewById(R.id.rv_list);//列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mList.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(Exercises3.this,initData());
        mList.setAdapter(mAdapter);


        //监听点击，跳转视频播放界面
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {

                System.out.println("position:"+position);
                //跳转，传值position（视频序号）
                Intent intent=new Intent(Exercises3.this, vedio.class);
                intent.putExtra("data",position);
                startActivity(intent);

            }
        });
    }


    //i：视频的编号，要求api只有10个视频所以i对10取余
    public List<String> initData() {
        List<String> datas = new ArrayList<String>();
        for (int i = 0; i <= 100; i++) {
            datas.add(describes[i%10]);
        }
        return datas;
    }


}

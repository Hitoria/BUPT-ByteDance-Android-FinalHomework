package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static final String TAG = "MyAdapter";
    private List<String> datas;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    private String[] pictures={
            "http://jzvd.nathen.cn/snapshot/f402a0e012b14d41ad07939746844c5e00005.jpg",
            "http://jzvd.nathen.cn/snapshot/8bd6d06878fc4676a62290cbe8b5511f00005.jpg",
            "http://jzvd.nathen.cn/snapshot/371ddcdf7bbe46b682913f3d3353192000005.jpg",
            "http://jzvd.nathen.cn/snapshot/dabe6ca3c71942fd926a86c8996d750f00005.jpg",
            "http://jzvd.nathen.cn/snapshot/edac56544e2f43bb827bd0e819db381000005.jpg",
            "http://jzvd.nathen.cn/snapshot/531f1e488eb84b898ae9ca7f6ba758ed00005.jpg",
            "http://jzvd.nathen.cn/snapshot/ad0331e78393457d88ded2257d9e47c800005.jpg",
            "http://jzvd.nathen.cn/snapshot/6ae53110f7fd470683587746f027698400005.jpg",
            "http://jzvd.nathen.cn/snapshot/ef384b95897b470c80a4aca4dd1112a500005.jpg",
            "http://jzvd.nathen.cn/snapshot/86a055d08b514c9ca1e76e76862105ec00005.jpg"};

    public MyAdapter(Context context, List<String> datas){
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView imageView;
        private VideoView videoView;

        //获取视频信息控件和封面控件
        public MyViewHolder(View itemView){
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.tv_title);
            imageView=(ImageView)itemView.findViewById(R.id.tv_image);

        }
    }
    public interface OnItemClickListener {
        public void onClick(View parent, int position);
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        this.mOnItemClickListener = l;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        Log.e(TAG,"create a new item");
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.im_list_item,parent,false));

        return holder;
    }

    @Override
    //列表下拉刷新视频，加载对应的视频信息和视频封面
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Log.e(TAG, "set value to item:" + position);
        final Uri uri = Uri.parse(pictures[position%10]);
        holder.title.setText(datas.get(position));
        Glide.with(holder.itemView)
                .load(uri)
                .into(holder.imageView);

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onClick(holder.itemView, pos);
                }
            });
        }

    }



    @Override
    public int getItemCount() {
        return datas.size();
    }


}

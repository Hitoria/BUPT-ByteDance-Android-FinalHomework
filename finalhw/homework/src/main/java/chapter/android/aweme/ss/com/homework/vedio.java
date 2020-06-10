package chapter.android.aweme.ss.com.homework;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class vedio extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;
    private SeekBar seekBar;
    private Button buttonPlay;
    private TextView textViewTime;
    private TextView textViewCurrentPosition;
    private TextView textViewStatus;
    private int anInt;
    private int a;
    private String[] videopaths={
            "http://jzvd.nathen.cn/video/1137e480-170bac9c523-0007-1823-c86-de200.mp4",
            "http://jzvd.nathen.cn/video/e0bd348-170bac9c3b8-0007-1823-c86-de200.mp4",
            "http://jzvd.nathen.cn/video/2f03c005-170bac9abac-0007-1823-c86-de200.mp4",
            "http://jzvd.nathen.cn/video/7bf938c-170bac9c18a-0007-1823-c86-de200.mp4",
            "http://jzvd.nathen.cn/video/47788f38-170bac9ab8a-0007-1823-c86-de200.mp4",
            "http://jzvd.nathen.cn/video/2d6ffe8f-170bac9ab87-0007-1823-c86-de200.mp4",
            "http://jzvd.nathen.cn/video/633e0ce-170bac9ab65-0007-1823-c86-de200.mp4",
            "http://jzvd.nathen.cn/video/2d6ffe8f-170bac9ab87-0007-1823-c86-de200.mp4",
            "http://jzvd.nathen.cn/video/51f7552c-170bac98718-0007-1823-c86-de200.mp4",
            "http://jzvd.nathen.cn/video/2a101070-170bad88892-0007-1823-c86-de200.mp4"
    };
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            if (videoView.isPlaying()) {
                int current = videoView.getCurrentPosition();
                seekBar.setProgress(current);
                textViewCurrentPosition.setText(time(videoView.getCurrentPosition()));
            }
            handler.postDelayed(runnable, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);

        a=getIntent().getIntExtra("data",anInt);
        final Uri uri = Uri.parse(videopaths[a%10]);

        videoView = (VideoView) this.findViewById(R.id.videoView);
        videoView.setVideoURI(uri);
        videoView.requestFocus();


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                textViewTime.setText(time(videoView.getDuration()));
                textViewStatus.setText("视频加载完毕");
                buttonPlay.setEnabled(true);
                //播放视频，开始线程更新进度条进度
                handler.postDelayed(runnable, 0);
                videoView.start();
                seekBar.setMax(videoView.getDuration());

            }
        });
        // 在播放完毕被回调
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(vedio.this, "播放完成", Toast.LENGTH_SHORT).show();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                // 发生错误重新播放
                play();
                Toast.makeText(vedio.this, "播放出错", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        textViewStatus.setText("玩命加载中");

        textViewTime = (TextView) findViewById(R.id.textViewTime);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        // 为进度条添加进度更改事件
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);

        textViewCurrentPosition = (TextView) findViewById(R.id.textViewCurrentPosition);

        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setEnabled(false);
        final Button buttonStop = (Button) findViewById(R.id.buttonStop);

        buttonPlay.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPlay:
                play();
                break;
            case R.id.buttonStop:
                stop();
                break;
            default:
                break;
        }
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        // 当进度条停止修改的时候触发
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // 取得当前进度条的刻度
            int progress = seekBar.getProgress();
            if (videoView.isPlaying()) {
                // 设置当前播放的位置
                videoView.seekTo(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {

        }
    };

    protected void play() {

        if (buttonPlay.getText().equals("播放")) {
            buttonPlay.setText("暂停");
            textViewStatus.setText("请您欣赏");
            // 开始线程，更新进度条的刻度
            handler.postDelayed(runnable, 0);
            videoView.start();
            seekBar.setMax(videoView.getDuration());

        } else

        {
            buttonPlay.setText("播放");
            if (videoView.isPlaying()) {
                videoView.pause();
            }
        }

    }

    protected void stop() {
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
    }
    protected String time(long millionSeconds) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millionSeconds);
        return simpleDateFormat.format(c.getTime());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

}
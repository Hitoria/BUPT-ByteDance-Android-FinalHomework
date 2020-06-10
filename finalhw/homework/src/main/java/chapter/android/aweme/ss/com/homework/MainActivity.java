package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static TextView tv_exer1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_exercises3).setOnClickListener(this);
    }

    @Override
    //监听跳转
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exercises3:
                startActivity(new Intent(this, Exercises3.class));
                break;
        }
    }
}

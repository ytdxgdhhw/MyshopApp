package com.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show=(TextView)findViewById(R.id.show);
        int array[][]={{1,2,3},{4,5,6},{7,8,9}};
        int sum=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (i==j||i+j==2){
                    sum+=array[i][j];
                }
            }
        }

        show.setText(sum+" ");
    }


}

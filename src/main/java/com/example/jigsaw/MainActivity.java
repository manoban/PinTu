package com.example.jigsaw;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.jigsaw.GamePintuLayout;
import com.example.jigsaw.GamePintuLayout.GamePintuListener;

public class MainActivity extends Activity
{

    private GamePintuLayout mGamePintuLayout;
    private TextView mLevel ;
    private TextView mTime;
    private Button bp1;
    private Button bp2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTime = (TextView) findViewById(R.id.id_time);
        mLevel = (TextView) findViewById(R.id.id_level);
        bp1 =(Button) findViewById(R.id.pause);
        bp2 = (Button) findViewById(R.id.resume);

        mGamePintuLayout = (GamePintuLayout) findViewById(R.id.id_gamepintu);
        mGamePintuLayout.setTimeEnabled(true);

        bp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
            }
        });

        bp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
            }
        });

        mGamePintuLayout.setOnGamePintuListener(new GamePintuListener()
        {
            @Override
            public void timechanged(int currentTime)
            {
                mTime.setText(""+currentTime);
            }

            @Override
            public void nextLevel(final int nextLevel)
            {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Game Info").setMessage("LEVEL UP !!!")
                        .setPositiveButton("NEXT LEVEL", new OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                mGamePintuLayout.nextLevel();
                                mLevel.setText(""+nextLevel);
                            }
                        }).show();
            }

            @Override
            public void gameover()
            {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Game Info").setMessage("Game over !!!")
                        .setPositiveButton("RESTART", new OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                mGamePintuLayout.restart();
                            }
                        }).setNegativeButton("QUIT",new OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                }).show();
            }
        });

    }

    @Override
    protected void onPause()
    {
        super.onPause();

        mGamePintuLayout.pause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mGamePintuLayout.resume();
    }

    public void openAlbum(){
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        } else {
            switch (requestCode) {
                case 2:
//                    Bitmap jigsaw = JigsawHelper.getInstance().getJigsaw(this, data.getData());
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}

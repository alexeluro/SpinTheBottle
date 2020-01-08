package com.example.spinthebottle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean isSpinning;
    private AppCompatImageView bottle;
    Random random = new Random();
    int initialDirection = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar();
        
        bottle = findViewById(R.id.bottle);

        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinBottle();
            }
        });
    }

    public void spinBottle(){
        if(isSpinning){
            int newDirection = random.nextInt(1800);
            float pivotX = bottle.getWidth() / 2;
            float pivotY = bottle.getHeight() / 2;

            Animation rotate = new RotateAnimation(initialDirection, newDirection, pivotX, pivotY);
//            Duration of the rotation
            rotate.setDuration(2800);
//            Save the position of the bottle after rotation and continue from there next time
            rotate.setFillAfter(true);

            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isSpinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isSpinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            initialDirection = newDirection;
            bottle.startAnimation(rotate);

        }
    }


    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_about){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setView(R.layout.inspired_coda);
            dialog.setCancelable(true);
            dialog.create();
            dialog.show();
        }
        return true;
    }
}

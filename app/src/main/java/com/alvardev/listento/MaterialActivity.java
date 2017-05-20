package com.alvardev.listento;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageView;

public class MaterialActivity extends AppCompatActivity {

    private ImageView iviLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        findViews();
        setActions();
    }

    private void findViews(){
        iviLogo = (ImageView) findViewById(R.id.ivi_logo);
    }

    private void setActions(){
        iviLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSecondActivity(iviLogo);
            }
        });
    }



    private void goToSecondActivity(View view){
        Intent intent = new Intent(
                MaterialActivity.this, //I'm here
                SecondActivity.class);//where to go

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
            ActivityOptions options = view != null ?
                    ActivityOptions.makeSceneTransitionAnimation(this, view, "view") :
                    ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent, options.toBundle());

        } else {
            startActivity(intent);
        }
    }


}

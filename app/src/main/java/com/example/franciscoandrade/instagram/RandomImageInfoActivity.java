package com.example.franciscoandrade.instagram;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RandomImageInfoActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appbar;

    LinearLayout imageContainer;

    RelativeLayout imagebkg;
    ImageView imageComplete;
    TextView likesText, userNameText, twiterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_image_info);

        collapsingToolbarLayout= findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Status Creator");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appbar=findViewById(R.id.appbar);

        //imagebkg=(RelativeLayout) findViewById(R.id.imagebkg);
        imageComplete=(ImageView)findViewById(R.id.imageComplete);
        likesText=(TextView)findViewById(R.id.likesText);
        userNameText=(TextView)findViewById(R.id.userNameText);
        twiterText=(TextView)findViewById(R.id.twiterText);
        Bundle extras= getIntent().getExtras();
        String imageUrl=extras.getString("imageUrl");
        String imageColor=extras.getString("imageColor");
        int imageHeight=extras.getInt("imageHeight");
        int imageLikes=extras.getInt("imageLikes");
        int imageWidth=extras.getInt("imageWidth");
        String imageUserName=extras.getString("imageUserName");
        String imageUserTwiter=extras.getString("imageUserTwiter");



        Picasso.with(getApplicationContext()).load(imageUrl)
                .into(imageComplete);

        likesText.setText(imageLikes + " Likes");
        userNameText.setText("Username: " + imageUserName);
        //twiterText.setText("Twiter: " + imageUserTwiter);

        showToolBar("", true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        collapsingToolbarLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(android.R.color.black));

    }

    private void showToolBar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

//                Intent intent = new Intent(CurrentActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

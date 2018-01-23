package com.example.franciscoandrade.instagram;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.franciscoandrade.instagram.UnsplashPOJO.Result;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class RandomImageInfoActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appbar;

    LinearLayout imageContainer;

    RelativeLayout imagebkg;
    TextView userNameText, twiterText;


    TextView likesText, twitterTV, userTVName, createdTV, descriptionTV;
    LinearLayout downloadHolder, portfolioHolder;
    ImageView imageComplete;
    CircleImageView profile_image;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_image_info);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Status Creator");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appbar = findViewById(R.id.appbar);

        //imagebkg=(RelativeLayout) findViewById(R.id.imagebkg);

        imageComplete = (ImageView) findViewById(R.id.imageComplete);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        likesText = (TextView) findViewById(R.id.likesText);
        twitterTV = (TextView) findViewById(R.id.twitterTV);
        userTVName = (TextView) findViewById(R.id.userTVName);
        createdTV = (TextView) findViewById(R.id.createdTV);
        descriptionTV = (TextView) findViewById(R.id.descriptionTV);
        downloadHolder = (LinearLayout) findViewById(R.id.downloadHolder);
        portfolioHolder = (LinearLayout) findViewById(R.id.portfolioHolder);

        Bundle sendObject = getIntent().getExtras();
        Result result = null;

        if (sendObject != null) {
            result = (Result) sendObject.getSerializable("result");
            Picasso.with(getApplicationContext()).load(result.getUrls().getRegular())
                    .into(imageComplete);
            likesText.setText(String.valueOf(result.getLikes()));
            twitterTV.setText(result.getUser().getUsername());
            userTVName.setText("By " + result.getUser().getUsername());
            createdTV.setText(result.getCreated_at().toString());

            if (result.getUser().getProfile_picture() == null) {
                profile_image.setVisibility(View.GONE);
            } else {
                Picasso.with(getApplicationContext()).load(result.getUser().getProfile_picture())
                        .into(profile_image);
            }

            if (result.getDescription() != null) {
                descriptionTV.setText(result.getDescription());
            } else {
                descriptionTV.setVisibility(View.GONE);
            }

            url = result.getUser().getPortfolio_url();

        }


        portfolioHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

//        Bundle extras= getIntent().getExtras();
//        String imageUrl=extras.getString("imageUrl");
//        String imageColor=extras.getString("imageColor");
//        int imageHeight=extras.getInt("imageHeight");
//        int imageLikes=extras.getInt("imageLikes");
//        int imageWidth=extras.getInt("imageWidth");
//        String imageUserName=extras.getString("imageUserName");
//        String imageUserTwiter=extras.getString("imageUserTwiter");


//        likesText.setText(imageLikes + " Likes");
//        userNameText.setText("Username: " + imageUserName);
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

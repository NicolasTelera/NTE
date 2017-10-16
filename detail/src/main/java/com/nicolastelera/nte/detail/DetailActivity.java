package com.nicolastelera.nte.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.nicolastelera.nte.base.CommonConstants;

import static com.nicolastelera.nte.base.CommonConstants.TRANSITION_NAME;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        displayPicture();
    }

    private void displayPicture() {
        final Intent intent = getIntent();
        final Uri uri = intent.getData();

        if (uri != null) {
            String path = uri.getPath();
            String param = path.substring(path.lastIndexOf('/') + 1);
            int index = Integer.parseInt(param);
            final ImageView imageView = findViewById(R.id.detailImageView);
            imageView.setTransitionName(TRANSITION_NAME + index);
            imageView.setImageResource((int) CommonConstants.images.get(index));
        }
    }
}

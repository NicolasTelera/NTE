package com.nicolastelera.nte.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.nicolastelera.nte.base.CommonConstants;

import static com.nicolastelera.nte.base.CommonConstants.DETAIL_URL;
import static com.nicolastelera.nte.base.CommonConstants.TRANSITION_NAME;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeGrid();
    }

    private void initializeGrid() {
        final GridLayout grid = findViewById(R.id.gridLayout);
        int index = 0;
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 4; column++) {
                addImageToGrid(grid, row, column, index++);
            }
        }
    }

    private void addImageToGrid(GridLayout grid, int row, int column, final int index) {
        final int photoId = (int) CommonConstants.images.get(index);

        final GridLayout.LayoutParams params = new GridLayout.LayoutParams(
            GridLayout.spec(row),
            GridLayout.spec(column, 1f)
        );
        params.setMargins(1, 1, 1, 1);
        params.width = 0;

        final ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(params);
        imageView.setImageResource(photoId);
        imageView.setTransitionName(TRANSITION_NAME + index);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPhotoDetail(view, index);
            }
        });

        grid.addView(imageView);
    }

    private void launchPhotoDetail(View view, @DrawableRes int index) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(DETAIL_URL + "/" + index));
        intent.setPackage(getPackageName());
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            view,
            TRANSITION_NAME + index
        );
        startActivity(intent, options.toBundle());
    }
}


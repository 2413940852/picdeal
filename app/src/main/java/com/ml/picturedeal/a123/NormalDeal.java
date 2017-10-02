package com.ml.picturedeal.a123;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ml.picturedeal.a123.PicDeal.ImageCrop;
import com.yalantis.ucrop.UCrop;

import java.io.File;

/**
 * Created by Administrator on 2017/9/23/023.
 */

public class NormalDeal extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_SELECT_PICTURE = 0x1;
    public static final String SAMPLE_CROPPED_IMAGE_NAME = "smap";

    ImageCrop ic;
    ImageButton ImageCroup,creatNewPic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_picdeal);
        initView();
    }

    private void initView(){
        ic          = findViewById(R.id.np_image_preview);
        ImageCroup  = findViewById(R.id.np_image_crop);
        creatNewPic = findViewById(R.id.nd_creat_new_pic);
        ImageCroup .setOnClickListener(this);
        creatNewPic.setOnClickListener(this);
    }
    private void upView(Bitmap bm) {
        if(bm != null){
            ic.setImage(bm);
        }
        ic.UpView();
        creatNewPic.setVisibility(View.INVISIBLE);
    }
    //打开图库
    private void pickFromGallery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent,getString(R.string.select_pic)), REQUEST_SELECT_PICTURE);
    }
    //裁剪activity
    private void startCropActivity() {
        String destinationFileName = SAMPLE_CROPPED_IMAGE_NAME;
        Uri uri = getImageUri();
        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));
        UCrop.Options options = new UCrop.Options();
        options.setFreeStyleCropEnabled(true);
        uCrop.withOptions(options);
        // uCrop = basisConfig(uCrop);
        // uCrop = advancedConfig(uCrop);

        uCrop.start(NormalDeal.this);
    }
    //将image转为uri
    private Uri getImageUri() {

        return null;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {
                final Uri selectedUri = data.getData();
                if (selectedUri != null) {
                    Bitmap bm = null;
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                        upView(bm);
//                        Cursor cursor = NormalDeal.this.getContentResolver().query(selectedUri, null, null, null, null);
//                        cursor.moveToFirst();
//                        String path = cursor.getString(1); // 图片文件路径
//                        cursor.close();
//                        Log.d("pic","图片路径"+path);

                    }catch (Exception e){ Log.d("pic","错误"+e);;}
                } else {
                    Toast.makeText(NormalDeal.this, "", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                // handleCropResult(data);
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            // handleCropError(data);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.np_image_crop:
                startCropActivity();
                break;
            case R.id.nd_creat_new_pic:
                pickFromGallery();
                break;

        }
    }


}

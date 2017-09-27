package com.ml.picturedeal.a123.PicDeal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/9/24/024.
 */

public class ImageCrop extends android.support.v7.widget.AppCompatImageView {

    private Paint  paint;
    private Bitmap cacheBit = null;
    private Context context;

    private int VIEW_WIDTH;
    private int VIEW_HEIGHT;
    private int IMAGE_X,IMAGE_Y = 10,IMAGE_WIDTH,IMAGE_HEIGHT;

    public ImageCrop(Context context, AttributeSet as){
        super(context,as);
        this.context = context;
        init();
    }
    public ImageCrop(Context context){
        super(context);
        this.context = context;
        init();
    }

    public void init(){
        paint = new Paint();
        setScaleType(ScaleType.CENTER);
    }

    public void setImage(Bitmap image){
        this.cacheBit = image;
    }

    public void UpView(){
        if(cacheBit != null) setImageBitmap(cacheBit);
    }
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawBitmap(cacheBit,0,0,paint);
//    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        VIEW_WIDTH  = w;
        VIEW_HEIGHT = h;
    }
}

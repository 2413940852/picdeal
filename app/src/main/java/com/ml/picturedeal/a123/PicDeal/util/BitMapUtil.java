package com.ml.picturedeal.a123.PicDeal.util;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;

/**
 * Created by Administrator on 2017/10/2/002.
 */

public class BitMapUtil {

    public static String saveBitmap(String outPutPath,String name,Bitmap.CompressFormat format, Bitmap mBitmap){
        File file = new File(outPutPath);
        if(file.exists()){
            file.mkdir();
        }
        File tmp = new File(file,"name");
        try{
            tmp.createNewFile();
            FileOutputStream fos = new FileOutputStream(tmp);
            mBitmap.compress(format,100,fos);
            fos.flush();fos.close();
        }catch (Exception e){e.printStackTrace();}
        return tmp.getAbsolutePath();
    }

}

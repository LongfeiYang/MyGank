package com.ylf.mygank.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Longfei Yang on 2016/7/26.
 * Email yanglongfei@yuanchuangyun.com
 */
public class ImageUtil {
    public static final String SAVE = "save";
    public static final String SHARE = "share";

    public static Uri saveImage(Context context, String url, Bitmap bitmap, ImageView imageView){
        return saveImage(context, url, bitmap, imageView, SAVE);
    }

    public static Uri getShareUri(Context context, String url, Bitmap bitmap, ImageView imageView){
        return saveImage(context, url, bitmap, imageView, SHARE);
    }

    public static Uri saveImage(Context context, String url, Bitmap bitmap, ImageView imageView, @NonNull String action){
        //存放路径
        String dir = Environment.getExternalStorageDirectory().getPath() + "/Gank";
        String sub = url.substring(url.lastIndexOf("/") + 1);
        String[] fileNameArray = sub.split("\\.");
        String fileName = fileNameArray[0] + ".png";
        //创建图片存放目录
        File fileDir = new File(dir);
        if (!fileDir.exists()){
            fileDir.mkdir();
        }
        //创建文件
        File imageFile = new File(fileDir, fileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(imageFile);
            boolean compass = bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            if (SAVE.equals(action)){
                if (compass){
                    Snackbar.make(imageView, "保存成功", Snackbar.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(imageView, "保存失败", Snackbar.LENGTH_SHORT).show();
                }
            }else{
                if (!compass){
                    Snackbar.make(imageView, "保存失败", Snackbar.LENGTH_SHORT).show();
                }
            }

            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Uri uri = Uri.fromFile(imageFile);
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        return uri;
    }

}

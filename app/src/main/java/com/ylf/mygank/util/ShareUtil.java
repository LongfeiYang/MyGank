package com.ylf.mygank.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Longfei Yang on 2016/7/26.
 * Email yanglongfei@yuanchuangyun.com
 */
public class ShareUtil {
    public static void shareText(Context context, String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, "分享干货"));
    }
    public static void shareImage(Context context, Uri uri){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");

        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, "分享福利"));
    }
    public static void copy2Clipboard(Context context, String url, View view){
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(ClipData.newPlainText("url", url));
        Snackbar.make(view, "复制链接成功", Snackbar.LENGTH_SHORT).show();
    }
}

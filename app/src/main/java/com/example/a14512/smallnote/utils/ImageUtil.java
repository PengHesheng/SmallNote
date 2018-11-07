package com.example.a14512.smallnote.utils;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

/**
 * @author 14512 on 2018/11/6
 */
public final class ImageUtil {

    public static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    public static String getImagePath(Context context, Intent data) {
        String imgPath;
        //19即4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4版本以上
            imgPath = getImgOnKitKat(context, data);
        } else {
            //4.4版本以下
            imgPath = getImgBeforeKitKat(context, data);
        }
        return imgPath;
    }

    public static Uri getImageUri(Context context, Intent data) {
        Uri uri;
        //19即4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4版本以上
            uri = getImgOnKitKatUri(context, data);
        } else {
            //4.4版本以下
            uri = data.getData();
        }
        return uri;
    }

    private static String getImgBeforeKitKat(Context context, Intent data) {
        Uri uri = data.getData();
        return getImagePath(context, uri, null);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String getImgOnKitKat(Context context, Intent data) {
        String imgPath = null;
        Uri uri = data.getData();
        if (uri != null) {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    //解析出数字格式的id
                    String id = docId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imgPath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    imgPath = getImagePath(context, contentUri, null);
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                imgPath = getImagePath(context, uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                imgPath = uri.getPath();
            }
        }
        return imgPath;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static Uri getImgOnKitKatUri(Context context, Intent data) {
        Uri uri = data.getData();
        if (uri != null) {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    return ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                }
            }
        }
        return uri;
    }
}

package com.app.file;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import com.app.file.bean.FileType;

import java.io.File;
import java.util.Comparator;

public class FileUtil {

    //获取文件类型
    public static FileType getFileType(File file) {
        if (file.isDirectory()) {
            return FileType.directory;
        }
        String fileName = file.getName().toLowerCase();

        if (fileName.endsWith(".mp3")) {
            return FileType.music;
        }

        if (fileName.endsWith(".mp4") || fileName.endsWith(".avi")
                || fileName.endsWith(".3gp") || fileName.endsWith(".mov")
                || fileName.endsWith(".rmvb") || fileName.endsWith(".mkv")
                || fileName.endsWith(".flv") || fileName.endsWith(".rm")) {
            return FileType.video;
        }

        if (fileName.endsWith(".txt") || fileName.endsWith(".log") || fileName.endsWith(".xml")) {
            return FileType.txt;
        }

        if (fileName.endsWith(".zip") || fileName.endsWith(".rar")) {
            return FileType.zip;
        }

        if (fileName.endsWith(".png") || fileName.endsWith(".gif")
                || fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")) {
            return FileType.image;
        }

        if (fileName.endsWith(".apk")) {
            return FileType.apk;
        }
        return FileType.other;
    }

    //文件按照名字排序
    public static Comparator comparator = new Comparator<File>() {
        @Override
        public int compare(File file1, File file2) {
            if (file1.isDirectory() && file2.isFile()) {
                return -1;
            } else if (file1.isFile() && file2.isDirectory()) {
                return 1;
            } else {
                return file1.getName().compareTo(file2.getName());
            }
        }
    };

    //获取文件的子文件个数
    public static int getFileChildCount(File file) {
        int count = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isHidden()) continue;
                count++;
            }
        }
        return count;
    }

    //文件大小转换
    public static String sizeToChange(long size) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");  //字符格式化，为保留小数做准备

        double G = size * 1.0 / 1024 / 1024 / 1024;
        if (G >= 1) {
            return df.format(G) + " GB";
        }

        double M = size * 1.0 / 1024 / 1024;
        if (M >= 1) {
            return df.format(M) + " MB";
        }

        double K = size * 1.0 / 1024;
        if (K >= 1) {
            return df.format(K) + " KB";
        }
        return size + " B";
    }

    //安装apk
    public static void openAppIntent(Context context, File file) {
        Uri path = null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isSDKN()) {
            path = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            path = Uri.fromFile(file);
        }
        intent.setDataAndType(path, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    //打开图片资源
    public static void openImageIntent(Context context, File file) {
        Uri path = null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isSDKN()) {
            path = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            path = Uri.fromFile(file);
        }
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(path, "image/*");
        context.startActivity(intent);
    }

    //打开文本资源
    public static void openTextIntent(Context context, File file) {
        Uri path = null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isSDKN()) {
            path = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            path = Uri.fromFile(file);
        }
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(path, "text/*");
        context.startActivity(intent);
    }

    //打开音频资源
    public static void openMusicIntent(Context context, File file) {
        Uri path = null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isSDKN()) {
            path = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            path = Uri.fromFile(file);
        }
        intent.setDataAndType(path, "audio/*");
        context.startActivity(intent);
    }

    //打开视频资源
    public static void openVideoIntent(Context context, File file) {
        Uri path = null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isSDKN()) {
            path = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            path = Uri.fromFile(file);
        }
        intent.setDataAndType(path, "video/*");
        context.startActivity(intent);
    }

    //打开所有能打开应用资源
    public static void openApplicationIntent(Context context, File file) {
        Uri path = null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isSDKN()) {
            path = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            path = Uri.fromFile(file);
        }
        intent.setDataAndType(path, "application/*");
        context.startActivity(intent);
    }

    //发送文件给第三方app
    public static void sendFile(Context context, File file) {
        Uri path = null;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isSDKN()) {
            path = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            path = Uri.fromFile(file);
        }
        intent.putExtra(Intent.EXTRA_STREAM, path);
        intent.setType("*/*");
        context.startActivity(Intent.createChooser(intent, "发送"));
    }

    //删除文件/文件夹
    public static boolean delete(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            file.delete();
        } else {
            for (File f : file.listFiles()) {
                //递归删除文件夹里的文件
                delete(f);
            }
            file.delete();
        }
        return true;
    }

    private static boolean isSDKN() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }
}

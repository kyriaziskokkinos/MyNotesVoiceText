package com.kokkinosk.mynotesvoicetext;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.format.Formatter;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;


class RecordingManager {

    private String dirPath;
    static File[] recordingArray;
    private WeakReference activityReference;
    private WeakReference activityContext;
    static ArrayList<Recording> recordingArrayList = new ArrayList<>();



    RecordingManager(Activity activity){
        if (activity!= null){
            activityReference = new WeakReference<>(activity);
            activityContext = new WeakReference<>(activity.getApplicationContext());
            dirPath = ((Activity)activityReference.get()).getFilesDir().getAbsolutePath()+"/Recordings";
            scanNewRecordings();

        }
    }


    void scanNewRecordings(){
        recordingArrayList.clear();
        File dir = new File(dirPath);
        recordingArray = dir.listFiles(getFileFilter());
        if (recordingArray == null) recordingArray = new File[0];
        for (File file : recordingArray) {
            recordingArrayList.add(
                    new Recording(
                            file.getName()
                            , 0L
                            , Uri.fromFile(file)
                            , Formatter.formatShortFileSize((Context)activityContext.get(), file.length())
                    )
            );
        }
        //return recordingArrayList.size();
    }


    public File[] getRecordingArray() {
        scanNewRecordings();
        return recordingArray;
    }

    ArrayList<Recording> getRecordingArrayList() {
        scanNewRecordings();
        return recordingArrayList;
    }

    void setRecordingArrayList(ArrayList<Recording> updated){
        recordingArrayList = updated;
    }

    private FileFilter getFileFilter(){
        return new FileFilter() {
            @Override
            public boolean accept(File file) {
                return ( file.getAbsolutePath().matches(".*\\.m4a") );
            }
        };
    }


    String getDirPath(){
        return dirPath;
    }


    FileFilter getRecordingFileFilter(){
        return new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.getAbsolutePath().matches(".*\\.m4a"));
            }
        };
    }


}
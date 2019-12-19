package com.example.jigsaw;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class ImagePiece {

    private  int index;//表示当前是第几块
    private Bitmap bitmap;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public String toString() {
        return "ImagePiece{" + "index=" + index + ", bitmap=" + bitmap + '}';
    }

    public ImagePiece() {
        this.index = index;
        this.bitmap = bitmap;
    }

}

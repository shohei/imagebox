package com.shoheiaoki.imagebox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by shohei on 7/2/16.
 */
public class ImageProcessActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

    private CameraBridgeViewBase mCameraView;
    private Mat mOutputFrame;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    mCameraView.enableView();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_process);

        String s = getIntent().getStringExtra(MainActivity.MAIN_TO_IMAGE_PROCESS);
        setActionBar(s);

        mCameraView = (CameraBridgeViewBase)findViewById(R.id.camera_view);
        mCameraView.setCvCameraViewListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 非同期でライブラリの読み込み/初期化を行う
        // static boolean initAsync(String Version, Context AppContext, LoaderCallbackInterface Callback)
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mLoaderCallback);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCameraView != null) {
            mCameraView.disableView();
        }
    }

    @Override
    public void onPause() {
        if (mCameraView != null) {
            mCameraView.disableView();
        }
        super.onPause();
    }

    protected void setActionBar(String title){
        setTitle(title);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        // Mat(int rows, int cols, int type)
        // rows(行): height, cols(列): width
        mOutputFrame = new Mat(height, width, CvType.CV_8UC1);
    }

    @Override
    public void onCameraViewStopped() {
        mOutputFrame.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        // Cannyフィルタをかける
        Imgproc.Canny(inputFrame.gray(), mOutputFrame, 80, 100);
        // ビット反転
        Core.bitwise_not(mOutputFrame, mOutputFrame);
        return mOutputFrame;
    }
}

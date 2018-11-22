package com.kery.total2demo.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.TextureView;
import android.widget.Button;

import com.kery.total2demo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Camera3Activity extends AppCompatActivity {
    TextureView textureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera3);
        textureView = (TextureView) findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                setupCamera();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }


    private void setupCamera() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        try {
            //遍历所有摄像头

                for (String id : manager.getCameraIdList()) {
                    CameraCharacteristics characteristics = manager.getCameraCharacteristics(id);
                    //默认打开后置摄像头
                    if (characteristics.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT)
                        continue;
                    //获取StreamConfigurationMap，它是管理摄像头支持的所有输出格式和尺寸
                    StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

                    // 对于静态图像捕获，我们使用最大的可用尺寸。
//                    mPreviewSize = Collections.max(
//                            Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)),
//                            new Comparator<Camera.Size>() {
//                                @Override
//                                public int compare(Camera.Size lhs, Camera.Size rhs) {
//                                    return Long.signum(lhs.width * lhs.height
//                                            - rhs.getHeight() * rhs.getWidth());
//                                }
//                            });
                    mCameraId = id;
                    break;
                }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openCamera() {
        //获取摄像头的管理者CameraManager
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        //检查权限
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            //打开相机，第一个参数指示打开哪个摄像头，第二个参数stateCallback为相机的状态回调接口，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
            manager.openCamera(mCameraId, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            mCameraDevice = camera;
            //开启预览
            startPreview();
        }

        @Override
        public void onDisconnected(CameraDevice camera) {

        }

        @Override
        public void onError(CameraDevice camera, int error) {

        }
    };





    private void startPreview() {
        SurfaceTexture mSurfaceTexture = textureView.getSurfaceTexture();

        //设置TextureView的缓冲区大小
        mSurfaceTexture.setDefaultBufferSize(mPreviewSize.width, mPreviewSize.height);

        //获取Surface显示预览数据
        Surface mSurface = new Surface(mSurfaceTexture);

        setupImageReader();

        //获取ImageReader的Surface
        Surface imageReaderSurface = mImageReader.getSurface();

        try {
            //创建CaptureRequestBuilder，TEMPLATE_PREVIEW比表示预览请求
            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            //设置Surface作为预览数据的显示界面
            mPreviewBuilder.addTarget(mSurface);
            //创建相机捕获会话，第一个参数是捕获数据的输出Surface列表，第二个参数是CameraCaptureSession的状态回调接口，当它创建好后会回调onConfigured方法，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
            mCameraDevice.createCaptureSession(Arrays.asList(mSurface, imageReaderSurface), mSessionStateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void setupImageReader() {

        //前三个参数分别是需要的尺寸和格式，最后一个参数代表每次最多获取几帧数据，本例的2代表ImageReader中最多可以获取两帧图像流
        mImageReader = ImageReader.newInstance(mPreviewSize.width, mPreviewSize.height,
                ImageFormat.JPEG, 2);

        //监听ImageReader的事件，当有图像流数据可用时会回调onImageAvailable方法，它的参数就是预览帧数据，可以对这帧数据进行处理
        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                mHandler.post(new ImageSaver(reader.acquireNextImage()));
            }
        }, mHandler);
    }
    private CameraCaptureSession.StateCallback mSessionStateCallback = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(CameraCaptureSession session) {
            try {
                //创建捕获请求
                mCaptureRequest = mPreviewBuilder.build();
                mPreviewSession = session;
                //设置反复捕获数据的请求，这样预览界面就会一直有数据显示
                mPreviewSession.setRepeatingRequest(mCaptureRequest, mSessionCaptureCallback, mHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConfigureFailed(CameraCaptureSession session) {

        }
    };
    private CameraCaptureSession.CaptureCallback mSessionCaptureCallback = new CameraCaptureSession.CaptureCallback() {

        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            //重启预览
            restartPreview();
        }
    };
    private void restartPreview() {
        try {
            //执行setRepeatingRequest方法就行了，注意mCaptureRequest是之前开启预览设置的请求
            mPreviewSession.setRepeatingRequest(mCaptureRequest, null, mHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    private HandlerThread mThreadHandler;
    private TextureView mPreviewView;
    private Handler mHandler = new Handler();
    private CaptureRequest.Builder mPreviewBuilder;
    private Button mButton;
    private ImageReader mImageReader;
    private String mCameraId;
    private Camera.Size mPreviewSize;
    private CameraDevice mCameraDevice;
    private CaptureRequest mCaptureRequest;
    private CameraCaptureSession mPreviewSession;
    public class ImageSaver implements Runnable {

        private Image mImage;
        private File mFile;

        public ImageSaver(Image image) {
            this.mImage = image;
        }

        @Override
        public void run() {
            ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            FileOutputStream output = null;

            SimpleDateFormat sdf = new SimpleDateFormat(
                    "yyyyMMdd_HHmmss",
                    Locale.US);

            String fname = "IMG_" +
                    sdf.format(new Date())
                    + ".jpg";
            mFile = new File(getApplication().getExternalFilesDir(null), fname);

            try {
                output = new FileOutputStream(mFile);
                output.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                mImage.close();
                if (null != output) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

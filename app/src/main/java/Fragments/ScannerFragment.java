package Fragments;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.asu.hebagp.MainActivity;
import com.asu.hebagp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mostafa on 2018/01/16.
 */

public class ScannerFragment extends Fragment {

    View view;
//    public static ImageView addIV;
    public static Uri imgURL;
    SurfaceView cameraSF;
    SurfaceHolder surfaceHolder;
    Camera.PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    Camera.PictureCallback jpegCallback;
    Camera camera;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scanner,container,false);

        MainActivity.activeScanner();

        initilizeComponents();

        return view;
    }

    private void initilizeComponents() {
//        addIV  = (ImageView) view.findViewById(R.id.image_add);

//        addIV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
//                        == PackageManager.PERMISSION_GRANTED ) //permision_accepted
//                {
//                    openImageIntent();
//
//                } else {
//                    // Show rationale and request permission.
//                    ActivityCompat.requestPermissions(getActivity(),
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
//
//                }
//            }
//        });

        cameraSF = (SurfaceView) view.findViewById(R.id.image_surface_view);

        surfaceHolder = cameraSF.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        rawCallback = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {

            }
        };

        /** Handles data for jpeg picture */
        shutterCallback = new Camera.ShutterCallback() {
            public void onShutter() {

            }
        };
        jpegCallback = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                try {
                    final File root;
                    root = new File(Environment.getExternalStorageDirectory() + File.separator + getActivity().getResources().getString(R.string.app_name) + File.separator);

                    if(!root.exists())
                    {
                        root.mkdirs();
                    }
                    final String fname = "img_"+ System.currentTimeMillis() + ".jpg";
                    final File sdImageMainDirectory = new File(root, fname);
                    outStream = new FileOutputStream(sdImageMainDirectory);
                    outStream.write(data);
                    outStream.close();
//                    Log.d("Log", "onPictureTaken - wrote bytes: " + data.length);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                } finally {
                }
//                Log.d("Log", "onPictureTaken - jpeg");
            }
        };

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start_camera();
            }
        },200);

        cameraSF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });
    }

    private void captureImage() {
        // TODO Auto-generated method stub
        camera.takePicture(shutterCallback, rawCallback, jpegCallback);
        Toast.makeText(getActivity(),"Image Captured",Toast.LENGTH_LONG).show();

    }

    private void start_camera()
    {
        try{
            camera = Camera.open();
        }catch(RuntimeException e){
//            Log.e(tag, "init_camera: " + e);
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
            return;
        }
        Camera.Parameters param;
        param = camera.getParameters();
        //modify parameter
        param.setPreviewFrameRate(20);
        camera.setParameters(param);
        camera.setDisplayOrientation(90);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            //camera.takePicture(shutter, raw, jpeg)
        } catch (Exception e) {
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
            return;
        }
    }

    private void stop_camera()
    {
        camera.stopPreview();
        camera.release();
    }

//    private void openImageIntent() {
//
//
//        final File root;
//        root = new File(Environment.getExternalStorageDirectory() + File.separator + getActivity().getResources().getString(R.string.app_name) + File.separator);
//
//        if(!root.exists())
//        {
//            root.mkdirs();
//        }
//        final String fname = "img_"+ System.currentTimeMillis() + ".jpg";
//        final File sdImageMainDirectory = new File(root, fname);
//        MainActivity.outputFileUri = Uri.fromFile(sdImageMainDirectory);
//
//        // Camera.
//        final List<Intent> cameraIntents = new ArrayList<Intent>();
//        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        final PackageManager packageManager = getActivity().getPackageManager();
//        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
//        for(ResolveInfo res : listCam) {
//            final String packageName = res.activityInfo.packageName;
//            final Intent intent = new Intent(captureIntent);
//            intent.setComponent(new ComponentName(packageName, res.activityInfo.name));
//            intent.setPackage(packageName);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, MainActivity.outputFileUri);
//            cameraIntents.add(intent);
//        }
//
//        // Filesystem.
//        final Intent galleryIntent = new Intent();
//        galleryIntent.setType("image/*");
//        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//
//        // Chooser of filesystem options.
//        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
//
//        // Add the camera options.
//        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));
//
//        getActivity().startActivityForResult(chooserIntent, MainActivity.selectPicCode);
//    }

}

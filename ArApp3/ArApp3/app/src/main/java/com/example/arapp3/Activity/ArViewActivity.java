package com.example.arapp3.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.arapp3.Constant;
import com.example.arapp3.R;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.LoadGltfListener;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Objects;

public class ArViewActivity extends AppCompatActivity {

    ArFragment arFragment;
    Button btnCapture;
    ProgressBar progressBar;

    String modelUrl = Constant.IMAGE_URL;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_view);

        progressBar = findViewById(R.id.progressBar);


        /*
          Bundle bundle = new Bundle();
                    bundle.putString("folderName", product.getFolderName());
                    bundle.putString("model", product.getModel());
         */

        //Get Intent
        Bundle bundle = getIntent().getExtras();
        String folderName = bundle.getString("folderName");
        String model = bundle.getString("model");
        Log.d("TAG", "onCreate: " + folderName);
        Log.d("TAG", "onCreate: " + model);

        modelUrl = modelUrl + folderName + "/" + model;








        //File Permission
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        // Ask for permission Read and Write External Storage
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
                String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            }
        }





        btnCapture = findViewById(R.id.capture);


        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);



        btnCapture.setOnClickListener(v -> {

            progressBar.setVisibility(View.VISIBLE);


            Toast.makeText(this, "cLICK", Toast.LENGTH_SHORT).show();

            //ArFragment mSceneView = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

            // Capture the screen shot of the scene view.
            final Bitmap bitmap = Bitmap.createBitmap(arFragment.getArSceneView().getWidth(), arFragment.getArSceneView().getHeight()   ,
                    Bitmap.Config.ARGB_8888); // mSceneView is my ArFragment
            final HandlerThread handlerThread = new HandlerThread("PixelCopier");
            handlerThread.start();


            // Make the request to copy.
            PixelCopy.request(arFragment.getArSceneView(), bitmap, (copyResult) -> {

                Log.d("TAG", "capture: " + copyResult);


                if (copyResult == PixelCopy.SUCCESS) {
                    try {


                        // Save the bitmap.
                        //android.media.MediaScannerConnection.scanFile(ArViewActivity.this, new String[]{android.provider.MediaStore.Images.Media.insertImage(ArViewActivity.this.getContentResolver(), bitmap, "ArView", null)}, null, null);
//                        MediaScannerConnection.scanFile(getApplicationContext(), new String[]{android.provider.MediaStore.Images.Media.insertImage(ArViewActivity.this.getContentResolver(), bitmap, "ArView", null)}, null,
//                                (path, uri) -> Log.i("TAG", "Finished scanning " + path));

                        // java.lang.NullPointerException: url then uri is null how to solve this error
//                        Uri uri = Uri.parse(android.provider.MediaStore.Images.Media.insertImage(ArViewActivity.this.getContentResolver(), bitmap, "ArView", null));
//                        android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, "Saved to Photos", android.widget.Toast.LENGTH_LONG);
//                        toast.show();
//                        Log.d("TAG", "capture: " + "Saved to Photos");


                        /*
//                        ContentValues values = new ContentValues();
//                        values.put(android.provider.MediaStore.Images.Media.TITLE, "ArView");
//                        values.put(android.provider.MediaStore.Images.Media.DISPLAY_NAME, "ArView");
//                        values.put(android.provider.MediaStore.Images.Media.DESCRIPTION, "ArView");
//                        values.put(android.provider.MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//                        values.put(android.provider.MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
//                        values.put(android.provider.MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
//                        values.put(android.provider.MediaStore.Images.Media.RELATIVE_PATH, "DCIM/" + "ArView");
//                        values.put(android.provider.MediaStore.Images.Media.IS_PENDING, true);
//
//
//                        Uri uri = getContentResolver().insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//                        Log.d("TAG", "capture: " + "Uri : " + String.valueOf(uri));

                         */

                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, "New Picture");
                        values.put(MediaStore.Images.Media.DISPLAY_NAME, "ArView");
                        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                        values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/ArView");
                        values.put(MediaStore.Images.Media.IS_PENDING, true);

                        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Log.d("TAG", "onCreate: " + uri);

                        // This is necessary because there is no guarantee that the file will be scanned after it is written.  This is mainly necessary because we are writing the file to a directory owned by the media scanner.

                        //Output uri : content://media/external/images/media/123456







                        if (uri != null) {
                            try {
                                OutputStream outputStream = getContentResolver().openOutputStream(uri);
                                assert outputStream != null;
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                                outputStream.close();
                                values.put(android.provider.MediaStore.Images.Media.IS_PENDING, false);
                                getContentResolver().update(uri, values, null, null);
                                android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, "Saved to Photos", android.widget.Toast.LENGTH_LONG);
                                toast.show();
                                Log.d("TAG", "capture: " + "Saved to Photos");
                            } catch (Exception e) {
                                android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, e.toString(), android.widget.Toast.LENGTH_LONG);
                                toast.show();
                                Log.d("TAG", "capture: " + "Error MediaScanner : " + e.toString());
                            }
                        } else {
                            android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, "Failed to copyPixels: " + copyResult, android.widget.Toast.LENGTH_LONG);
                            toast.show();
                            Log.d("TAG", "capture: " + "Failed to copyPixels -----: " + copyResult);
                        }







                    } catch (Exception e) {
                        android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, e.toString(), android.widget.Toast.LENGTH_LONG);
                        toast.show();
                        Log.d("TAG", "capture: " + "Error MediaScanner : " + e.toString());

                    } finally {
                       Log.d("TAG", "capture: " + "MediaScanner : " + "Completed");
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                } else {
                    android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, "Failed to copyPixels: " + copyResult, android.widget.Toast.LENGTH_LONG);
                    toast.show();
                    Log.d("TAG", "capture: " + "Failed to copyPixels: " + copyResult);
                    progressBar.setVisibility(View.INVISIBLE);
                }
                handlerThread.quitSafely();
            }, new android.os.Handler(handlerThread.getLooper()));

            /*

            // Create a filename that is unique enough that we don't have a conflict
            // trying to write to a file that already exists.  Otherwise, demo appends
            // to the file.
            String filename = "ArView" + new Date().getTime() + ".jpg";
           // File out = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), filename);
            //Store in Phone Gallery
            File out = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), filename);
            try {
                FileOutputStream outputStream = new FileOutputStream(out);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, "Saved to Photos", android.widget.Toast.LENGTH_LONG);
                toast.show();
            } catch (Exception e) {
                android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, e.toString(), android.widget.Toast.LENGTH_LONG);
                toast.show();
                Log.d("TAG", "capture: " + "Error: " + e.toString());
            }
            */




        });


        assert arFragment != null;
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {

                Anchor anchor = hitResult.createAnchor();

//                ModelRenderable.builder()
//                        .setSource(ArViewActivity.this, R.raw.potted_plant_01_4k)
//                        .build()
//                        .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
//                        .exceptionally(throwable -> {
////                                    Create a toast
//                            android.widget.Toast.makeText(ArViewActivity.this, "Error", android.widget.Toast.LENGTH_SHORT).show();
//
//                            return null;
//                        });

//  Error:
//                String GLTF_ASSET = "https://github.com/KhronosGroup/glTF-Sample-Models/blob/main/2.0/Duck/glTF/Duck.gltf";
//                String GLTF_ASSET = "http://192.168.0.159:8080/files/1709211445097/1709211445097BedDouble.gltf";
//                String GLTF_ASSET = "http://192.168.0.159:8080/files/123/Duck.gltf";
                String GLTF_ASSET = modelUrl;
                ModelRenderable.builder()
                        .setSource(ArViewActivity.this, RenderableSource.builder().setSource(ArViewActivity.this, Uri.parse(GLTF_ASSET), RenderableSource.SourceType.GLTF2)
                                .setScale(0.5f)  // Scale the original model to 50%.
                                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                                .build())
                        .build()
                        .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                        .exceptionally(
                                throwable -> {
                                   Log.d("TAG", "onTapPlane: " + throwable.toString());
                                    return null;
                                });
/* ava.util.concurrent.CompletionException: android.util.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 7 column 10*\
Solution:

 */





//                ModelRenderable.builder()
//                        .setSource(ArViewActivity.this, Uri.parse(GLTF_ASSET), RenderableSource.SourceType.GLTF2)
//                        .build()
//                        .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
//                        .exceptionally(throwable -> {
////                                    Create a toast
//                            android.widget.Toast.makeText(ArViewActivity.this, "Error", android.widget.Toast.LENGTH_SHORT).show();
//
//                            return null;
//                        });

            }
        });
    }



    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);

        TransformableNode transformableNode = new com.google.ar.sceneform.ux.TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);

        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }


//    public void capture( android.view.View view) {
//
//        ArFragment mSceneView = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
//
//        // Capture the screen shot of the scene view.
//        final Bitmap bitmap = Bitmap.createBitmap(mSceneView.getArSceneView().getWidth(), mSceneView.getArSceneView().getHeight()   ,
//                Bitmap.Config.ARGB_8888); // mSceneView is my ArFragment
//        final HandlerThread handlerThread = new HandlerThread("PixelCopier");
//        handlerThread.start();
//        // Make the request to copy.
//        PixelCopy.request(mSceneView.getArSceneView(), bitmap, (copyResult) -> {
//
//            Log.d("TAG", "capture: " + copyResult);
//
//            if (copyResult == PixelCopy.SUCCESS) {
//                try {
//                    // Save the bitmap.
//                    //android.media.MediaScannerConnection.scanFile(ArViewActivity.this, new String[]{android.provider.MediaStore.Images.Media.insertImage(ArViewActivity.this.getContentResolver(), bitmap, "ArView", null)}, null, null);
//                    MediaScannerConnection.scanFile(getApplicationContext(), new String[]{android.provider.MediaStore.Images.Media.insertImage(ArViewActivity.this.getContentResolver(), bitmap, "ArView", null)}, null,
//                            (path, uri) -> Log.i("TAG", "Finished scanning " + path));
//
//
//                    Log.d("TAG", "capture: " + "Success");
//
//                } catch (Exception e) {
//                    android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, e.toString(), android.widget.Toast.LENGTH_LONG);
//                    toast.show();
//                    Log.d("TAG", "capture: " + "Error: " + e.toString());
//                }
//            } else {
//                android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, "Failed to copyPixels: " + copyResult, android.widget.Toast.LENGTH_LONG);
//                toast.show();
//
//                Log.d("TAG", "capture: " + "Failed to copyPixels: " + copyResult);
//            }
//
//
//            handlerThread.quitSafely();
//        }, new android.os.Handler(handlerThread.getLooper()));
//
//    }


//    protected static File takeScreenshot(View view) {
//        Date date = new Date();
//        CharSequence format = android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
//
//
//        try {
//            String dirPath = Environment.getExternalStorageDirectory().toString() + "/Screenshots";
//            File fileDir = new File(dirPath);
//            if (!fileDir.exists()) {
//                boolean mkdir = fileDir.mkdir();
//            }
//            String path = dirPath + "/" + format + ".jpeg";
//            view.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
//            view.setDrawingCacheEnabled(false);
//            File imageFile = new File(path);
//            FileOutputStream outputStream = new FileOutputStream(imageFile);
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
//            outputStream.flush();
//            outputStream.close();
//            return imageFile;
//
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//
//
//
//        return null;
//    }


}

/*
    public void capture( android.view.View view) {

        ArFragment mSceneView = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

        // Capture the screen shot of the scene view.
        final Bitmap bitmap = Bitmap.createBitmap(mSceneView.getArSceneView().getWidth(), mSceneView.getArSceneView().getHeight()   ,
                Bitmap.Config.ARGB_8888); // mSceneView is my ArFragment
        final HandlerThread handlerThread = new HandlerThread("PixelCopier");
        handlerThread.start();
        // Make the request to copy.
        PixelCopy.request(mSceneView.getArSceneView(), bitmap, (copyResult) -> {
            if (copyResult == PixelCopy.SUCCESS) {
                try {
                    // Save the bitmap.
                    android.media.MediaScannerConnection.scanFile(ArViewActivity.this, new String[]{android.provider.MediaStore.Images.Media.insertImage(ArViewActivity.this.getContentResolver(), bitmap, "ArView", null)}, null, null);
                } catch (Exception e) {
                    android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, e.toString(), android.widget.Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, "Failed to copyPixels: " + copyResult, android.widget.Toast.LENGTH_LONG);
                toast.show();
            }
            handlerThread.quitSafely();
        }, new android.os.Handler(handlerThread.getLooper()));


    }
 */

/*
 //Take ScreenShot
            Date date = new Date();
            CharSequence format = android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);



            try {
                String dirPath = Environment.getExternalStorageDirectory().toString() + "/Screenshots";
                File fileDir = new File(dirPath);
                if (!fileDir.exists()) {
                    boolean mkdir = fileDir.mkdir();
                }
                String path = dirPath + "/" + format + ".jpeg";
                v.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
                v.setDrawingCacheEnabled(false);
                File imageFile = new File(path);
                FileOutputStream outputStream = new FileOutputStream(imageFile);
                int quality = 100;
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                outputStream.flush();
                outputStream.close();
                Toast.makeText(ArViewActivity.this, "Saved to Gallery", Toast.LENGTH_SHORT).show();
                MediaScannerConnection.scanFile(ArViewActivity.this, new String[]{imageFile.getAbsolutePath()}, null, null);
                Uri savedImageURI = Uri.parse(imageFile.getAbsolutePath());
                Log.d("TAG", "onClick: " + savedImageURI);
            } catch (Throwable e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to Save"+e.toString(), Toast.LENGTH_SHORT).show();
            }
        */
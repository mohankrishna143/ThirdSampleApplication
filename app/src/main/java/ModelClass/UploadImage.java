package ModelClass;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.sampleapplication.R;
import com.android.sampleapplication.SecondFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UploadImage {

    private static final float ROTATE_FROM = 00.0f;
    private static final float ROTATE_TO = 360.0f;
    private static final int DURATION = 1000;
    SecondFragment ctx;

    ArrayList<String> spinnerData=new ArrayList<>();

    public UploadImage(SecondFragment context){
        this.ctx=context;
        spinnerData.add(" Select ");
        spinnerData.add("physiotherapist ");
        spinnerData.add("Gynecologist ");
        spinnerData.add("Dermatologist");
        spinnerData.add("Dentist");
        spinnerData.add("Neurologist");

    }


    public class uploadImage extends AsyncTask<String, Void, String> {
        File newfile;
        Intent image_Data;
        String imagePath;
        public uploadImage(Intent data) {
            this.image_Data=data;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ctx.tv_title.setText("Uploading please wait....");
            clockWiseAnim();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
               AssetFileDescriptor videoAsset =ctx.getActivity().getContentResolver().openAssetFileDescriptor(image_Data.getData(), "r");
                FileInputStream in = videoAsset.createInputStream();
                String filePath=getPath(image_Data.getData());
                imagePath= filePath.substring(filePath.lastIndexOf('/') + 1);
                File filepath = Environment.getExternalStorageDirectory();
                File dir = new File(filepath.getAbsolutePath() + "/" + "Test" + "/");
                if (!dir.exists()) {
                    dir.mkdirs();
                }else{
                    deleteImages();
                    dir.mkdirs();
                }

                newfile = new File(dir, "save_" + System.currentTimeMillis() + ".mp4");

                if (newfile.exists()) newfile.delete();


                OutputStream out = new FileOutputStream(newfile);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();

                Log.v("", "Copy file successful.");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ctx.btn_upload.clearAnimation();
            ctx.tv_title.setText(imagePath);
            Toast.makeText(ctx.getActivity(), "Uploaded Successfully",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = ctx.getActivity().getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {

            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

   /* private  String getImagePath(Uri image){
        String picturePath;
        String[] filePath = { MediaStore.Images.Media.DATA };
        Cursor c = getActivity().getContentResolver().query(
                image, filePath, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePath[0]);
        picturePath = c.getString(columnIndex);
        c.close();
        return picturePath;
    }*/
   /* public void uploadImage(){
        try {
            clockWiseAnim();

            File newfile;

            AssetFileDescriptor videoAsset =ctx.getActivity().getContentResolver().openAssetFileDescriptor(data.getData(), "r");
            FileInputStream in = videoAsset.createInputStream();

            File filepath = Environment.getExternalStorageDirectory();
            File dir = new File(filepath.getAbsolutePath() + "/" + "Test" + "/");
            if (!dir.exists()) {
                dir.mkdirs();
            }else{
                deleteImages();
                dir.mkdirs();
            }

            newfile = new File(dir, "save_" + System.currentTimeMillis() + ".mp4");

            if (newfile.exists()) newfile.delete();


            OutputStream out = new FileOutputStream(newfile);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();

            Log.v("", "Copy file successful.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    private void clockWiseAnim(){

        RotateAnimation r = new RotateAnimation(ROTATE_FROM,
                ROTATE_TO, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration(DURATION);
        r.setRepeatCount(Animation.INFINITE);
        r.setFillAfter(true);
        r.setInterpolator(new LinearInterpolator());

        ctx.btn_upload.startAnimation(r);
      /*Animation animation1 = AnimationUtils.loadAnimation(getActivity(),
              R.anim.rounded_anim);
      btn_upload.startAnimation(animation1);*/
    }

    private  void deleteImages(){
        try {
            String root = Environment.getExternalStorageDirectory().getAbsolutePath();
            File myDir = new File(root + "/Test");
            //myDir.delete();
            File[] files = myDir.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                files[i].delete();
                //Log.d("Files", "FileName:" + files[i].getName());
            }
            myDir.delete();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void spinnerAdapter(){
        ArrayAdapter<String> scr_adapter = new ArrayAdapter<String>(ctx.getActivity(),R.layout.subject_item, spinnerData);
        scr_adapter.setDropDownViewResource(R.layout.subject_item);
        ctx.spinner.setAdapter(scr_adapter);
    }

    public boolean validateText(String urlString){
           /* Pattern p = Patterns.WEB_URL;
            Matcher m = p.matcher(text.toLowerCase());
            return m.matches();*/
       // URL url = new URL(urlString);
        return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches();



    }
}

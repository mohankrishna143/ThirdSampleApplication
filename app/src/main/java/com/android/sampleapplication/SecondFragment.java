package com.android.sampleapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ModelClass.UploadImage;


public class SecondFragment extends Fragment implements View.OnClickListener {
    int tabCount;
    public Spinner spinner;
    ListView list;
    int pick = 100;
    public Button btn_upload,btn_choose,btn_next;
    public TextView tv_title,tv_path;
    int PICK_IMAGE=401;
    TextInputEditText et_url;
    TextInputLayout txt_url;

    UploadImage upload;
    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.secondlayout, container, false);
        initViews(view);


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == pick) {
            if(data!=null) {
                upload.new uploadImage(data).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }else
        if (requestCode == PICK_IMAGE) {
            //TODO: action
            //final Bundle extras = data.getExtras();
            if ( data!=null&&data.getData() != null) {
                //Get image
                Uri selectedImageUri = data.getData();
                //selectedImagePath = getPath(selectedImageUri);
                String path = upload.getPath(selectedImageUri);
                String fileName = path.substring(path.lastIndexOf("/") + 1);
                tv_path.setText(fileName);
                btn_choose.setText(fileName);

            }
        }


    }

    private void initViews(View v) {
        upload=new UploadImage(SecondFragment.this);
        btn_upload=v.findViewById(R.id.btn_upload);
        tv_title=v.findViewById(R.id.tv_title);
        spinner= v.findViewById(R.id.spinner);
        btn_choose=v.findViewById(R.id.btn_choose);
        tv_path=v.findViewById(R.id.tv_path);
        txt_url=v.findViewById(R.id.txt_url);
        et_url=v.findViewById(R.id.et_url);
        btn_next=v.findViewById(R.id.btn_next);

        btn_upload.setOnClickListener(this);
        btn_choose.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        upload.spinnerAdapter();
        et_url.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = et_url.getText().toString();
                if(!upload.validateText(text)){
                    if (!txt_url.isErrorEnabled()) {
                        txt_url.setErrorEnabled(true);
                        txt_url.setError("Enter Valid Url");
                    }else{
                        txt_url.setError("Enter Valid Url");
                    }
                }else{
                    if (txt_url.isErrorEnabled()) {
                        txt_url.setErrorEnabled(false);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /*
     * Button Click Event
     */
    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.btn_upload:
                    /*requestPermission();*/
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, pick);
                    break;

                case R.id.btn_choose:
                    Intent img_intent = new Intent();
                    img_intent.setType("image/*");
                    img_intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(img_intent, "Select Picture"), PICK_IMAGE);
                    break;

                case R.id.btn_next:
                    ((MainActivity) getActivity()).nextFragment(2);
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }












}

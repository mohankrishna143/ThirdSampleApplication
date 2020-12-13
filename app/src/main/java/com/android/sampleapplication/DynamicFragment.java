package com.android.sampleapplication;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;



public class DynamicFragment extends Fragment implements View.OnClickListener {
    TextInputEditText et_count, et_pwd, et_username;
    RadioButton radio_signup, radio_tabCount;
    LinearLayout layout_signup, layout_tab;
    TextInputLayout txt_user_input, txt_pwd_input,et_input_count;
    Context mContext;
    int tabCount;
    String USERNAME = "userName";
    String PASSWORD = "Password";

    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dynamic_fragment_layout, container, false);

        mContext = getActivity();
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        Button btn_signup = view.findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(this);
        et_pwd = view.findViewById(R.id.et_pwd);
        et_username = view.findViewById(R.id.et_username);
        txt_user_input = view.findViewById(R.id.txt_user_input);
        txt_pwd_input = view.findViewById(R.id.txt_pwd_input);

        layout_signup = (LinearLayout) view.findViewById(R.id.layout_signup);
       /// tabCount = ((MainActivity) getActivity()).getPagerCOunt();

        /*if (tabCount == 1) {
            btn_signup.setText("Submit");
        }*/
        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txt_user_input.isErrorEnabled()) {
                    txt_user_input.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
       /* et_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_input_count.isErrorEnabled()) {
                    et_input_count.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txt_pwd_input.isErrorEnabled()) {
                    txt_pwd_input.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                case R.id.btn_signup:
                    String userName = et_username.getText().toString();
                    String password = et_pwd.getText().toString();
                    if (userName != null && userName.length() == 0) {
                        if (!txt_user_input.isErrorEnabled()) {
                            txt_user_input.setErrorEnabled(true);
                        }
                        txt_user_input.setError("Enter UserName");
                    } else if (password != null && password.length() == 0) {
                        if (!txt_pwd_input.isErrorEnabled()) {
                            txt_pwd_input.setErrorEnabled(true);
                        }
                        txt_pwd_input.setError("Enter Password");
                    } else if (userName != null && userName.length() > 0 && password != null && password.length() > 0) {
                        ((MainActivity) mContext).nextFragment(1);

                    }

                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

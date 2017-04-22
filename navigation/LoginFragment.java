package test.android.com.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import test.android.com.navdrawerexample.R;

/**
 * Created by Taresh Tank on 12/27/2016.
 */

public class LoginFragment extends Fragment {

    EditText EmailEt,PasswordEt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login,container,false);

        EmailEt = (EditText)rootView.findViewById(R.id.et_email_login);
        PasswordEt = (EditText)rootView.findViewById(R.id.et_password_login);
        ImageButton imageButton = (ImageButton)rootView.findViewById(R.id.OnLogin);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailEt.getText().toString();
                String password = PasswordEt.getText().toString();
                String type = "login";
                BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity()); // or getApplicationContext()
                AsyncTask<String, Void, String> res = backgroundWorker.execute(type,email,password);
            }
        });
        TextView textView = (TextView)rootView.findViewById(R.id.tv_reg);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame,new RegisterFragment()).commit();
            }
        });

        return rootView;
    }


    @Override
    public void onPause() {
        super.onPause();
    }

}

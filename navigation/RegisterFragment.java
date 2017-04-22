package test.android.com.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import test.android.com.navdrawerexample.R;

/**
 * Created by Taresh Tank on 12/27/2016.
 */

public class RegisterFragment extends Fragment {
    EditText name,surname,mobile,email,password,cnf_password;
    RadioGroup radioSexGroup;
    RadioButton radioSexButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_register,container,false);

        name = (EditText)rootView.findViewById(R.id.et_fname);
        surname = (EditText)rootView.findViewById(R.id.et_lname);
        radioSexGroup = (RadioGroup)rootView.findViewById(R.id.radioSex);
        mobile = (EditText)rootView.findViewById(R.id.et_mobile);
        email = (EditText)rootView.findViewById(R.id.et_email);
        password = (EditText)rootView.findViewById(R.id.et_Password);
        cnf_password = (EditText)rootView.findViewById(R.id.et_cnf_Password);

        ImageButton imageButton  = (ImageButton)rootView.findViewById(R.id.btn_reg);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = name.getText().toString();
                String str_surname = surname.getText().toString();
                String str_mobile = mobile.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();
                String str_cnf_password = cnf_password.getText().toString();
                String str_radio;
                {//Radio Sex Button
                    // get selected radio button from radioGroup
                    int selectedId = radioSexGroup.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    radioSexButton = (RadioButton)rootView.findViewById(selectedId);
                    str_radio = radioSexButton.getText().toString();

                }
                if(str_mobile.isEmpty() || str_email.isEmpty() || str_name.isEmpty() || str_surname.isEmpty() || str_password.isEmpty() || str_cnf_password.isEmpty() ||str_radio.isEmpty()){
                    Toast.makeText(getActivity(),"Please enter all the enteries",Toast.LENGTH_SHORT).show();
                }
                else if(str_password.length()<8){
                    Toast.makeText(getActivity(),"Password length must be at least 8 characters",Toast.LENGTH_SHORT).show();
                }
                else if( !str_password.equals(str_cnf_password)){
                    Toast.makeText(getActivity(),"Password Didn't Match Retry",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String type = "register";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                    backgroundWorker.execute(type, str_name, str_surname, str_email, str_password,str_radio,str_mobile);
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.content_frame,new MainFragment()).commit();
                 }
            }
        });
        return rootView;
    }

}

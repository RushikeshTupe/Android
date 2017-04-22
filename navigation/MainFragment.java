package test.android.com.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.android.com.navdrawerexample.R;

/**
 * Created by Taresh Tank on 12/27/2016.
 */

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //We can use the layout inflator to inflate the view.

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }
}

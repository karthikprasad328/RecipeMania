package app.com.example.karthik.recipemania;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Karthik on 4/24/2015.
 */
public class Fragment_About extends Fragment {

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.help, container, false);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("About Us");
        final ImageButton imageButton = (ImageButton) v.findViewById(R.id.imageView);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Flash)
                        .duration(1700)
                        .playOn(imageButton);
            }
        });
        return v;

    }
}

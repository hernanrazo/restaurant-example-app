package hernanrazo.fakerestaurant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class orderFragment extends Fragment {
    public orderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        Spinner appetizerSpinner = view.findViewById(R.id.appetizerSpinner);






//        ArrayAdapter<CharSequence> appitizerAdapter = ArrayAdapter.createFromResource(this,
//                R.spinnerAppetizerStrings,
//                android.R.layout.appetizerSpinner);

        return view;

    }
}
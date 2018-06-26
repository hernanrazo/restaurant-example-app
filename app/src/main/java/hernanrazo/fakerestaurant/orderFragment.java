package hernanrazo.fakerestaurant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class orderFragment extends Fragment {
    public orderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        Spinner appetizerSpinner = view.findViewById(R.id.appetizerSpinner);
        ArrayAdapter<CharSequence> appetizerAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                                                                                      R.array.spinnerAppetizerStrings,
                                                                                      android.R.layout.simple_spinner_item);

        appetizerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appetizerSpinner.setAdapter(appetizerAdapter);

        Spinner entreeSpinner = view.findViewById(R.id.entreeSpinner);
        ArrayAdapter<CharSequence> entreeAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                                                                                   R.array.spinnerEntreeStrings,
                                                                                   android.R.layout.simple_spinner_item);

        entreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        entreeSpinner.setAdapter(entreeAdapter);

        Spinner kidsMenuSpinner = view.findViewById(R.id.kidsMenuSpinner);
        ArrayAdapter<CharSequence> kidsMenuAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                                                                                     R.array.spinnerKidsMenuStrings,
                                                                                     android.R.layout.simple_spinner_item);

        kidsMenuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kidsMenuSpinner.setAdapter(kidsMenuAdapter);

        Spinner sideDishSpinner = view.findViewById(R.id.sideDishSpinner);
        ArrayAdapter<CharSequence> sideDishAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                                                                                     R.array.spinnerSideDishStrings,
                                                                                     android.R.layout.simple_spinner_item);

        sideDishAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sideDishSpinner.setAdapter(kidsMenuAdapter);

        Spinner desertSpinner = view.findViewById(R.id.desertSpinner);
        ArrayAdapter<CharSequence> desertAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                                                                                   R.array.spinnerDesertStrings,
                                                                                   android.R.layout.simple_spinner_item);

        desertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desertSpinner.setAdapter(desertAdapter);

        return view;
    }
}
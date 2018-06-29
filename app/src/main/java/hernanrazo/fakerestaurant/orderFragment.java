package hernanrazo.fakerestaurant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class orderFragment extends Fragment {

    Button orderButton;

    public orderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container, false);
        orderButton = view.findViewById(R.id.orderButton);

        //set up all spinners for each section of the menu
        Spinner appetizerSpinner = view.findViewById(R.id.appetizerSpinner);
        ArrayAdapter<CharSequence> appetizerAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                                                                                      R.array.spinnerAppetizerStrings,
                                                                                      android.R.layout.simple_spinner_item);

        appetizerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appetizerSpinner.setAdapter(appetizerAdapter);

        //setup listeners
        appetizerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                String item = adapterView.getItemAtPosition(position).toString();

                switch(item) {
                    
                    case "French Fries":
                        Toast.makeText(getActivity().getBaseContext(), "Selected: French Fries", Toast.LENGTH_SHORT).show();
                        break;


                    case "Onion Rings":
                        Toast.makeText(getActivity().getBaseContext(), "Selected: Onion Rings", Toast.LENGTH_SHORT).show();
                        break;


                    case "Small Salad":
                        Toast.makeText(getActivity().getBaseContext(), "Selected: Small Salad", Toast.LENGTH_SHORT).show();
                        break;

                    case "Chicken Fingers":
                        Toast.makeText(getActivity().getBaseContext(), "Selected: Chicken Fingers", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner entreeSpinner = view.findViewById(R.id.entreeSpinner);
        ArrayAdapter<CharSequence> entreeAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                                                                                   R.array.spinnerEntreeStrings,
                                                                                   android.R.layout.simple_spinner_item);

        entreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        entreeSpinner.setAdapter(entreeAdapter);

        entreeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                String item = adapterView.getItemAtPosition(position).toString();

                switch(item) {

                    case "Cheese Burger":
                        break;
                    case "Caeser Salad":
                        break;
                    case "Basic Salad":
                        break;
                    case "Steak":
                        break;
                    case "Tacos De Lengua":
                        break;
                    case "Spicy Fish Fillet":
                        break;
                    case "Rptisserie Chicken":
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
        sideDishSpinner.setAdapter(sideDishAdapter);

        Spinner desertSpinner = view.findViewById(R.id.desertSpinner);
        ArrayAdapter<CharSequence> desertAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                                                                                   R.array.spinnerDesertStrings,
                                                                                   android.R.layout.simple_spinner_item);

        desertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desertSpinner.setAdapter(desertAdapter);
        
        return view;
    }
}
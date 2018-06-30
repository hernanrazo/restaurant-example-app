package hernanrazo.fakerestaurant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class confirmOrderFragment extends Fragment {

    Button backButton;

    public confirmOrderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction backToOrder = getFragmentManager().beginTransaction();
                backToOrder.replace(R.id.mainContent, new orderFragment());
                backToOrder.commit();
            }
        });

        return view;
    }
}
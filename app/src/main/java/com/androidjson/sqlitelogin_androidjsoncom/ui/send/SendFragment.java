package com.androidjson.sqlitelogin_androidjsoncom.ui.send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.androidjson.sqlitelogin_androidjsoncom.R;
public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;
    private Button btn;
    private EditText ename, eemail, feedbackfield;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        ename = (EditText) root.findViewById(R.id.ename);
        String name = ename.getText().toString();
        eemail = (EditText) root.findViewById(R.id.eemail);
        String mail = eemail.getText().toString();
        feedbackfield = (EditText) root.findViewById(R.id.emess);
        btn = (Button) root.findViewById(R.id.bttn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eemail.getText().toString().isEmpty() || ename.getText().toString().isEmpty() || feedbackfield.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Complete all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                    if (eemail.getText().toString().trim().matches(emailPattern)) {
                        Toast.makeText(getContext(), "We got it!", Toast.LENGTH_SHORT).show();
                        ename.setText(null);
                        eemail.setText(null);
                        feedbackfield.setText(null);
                                           }

                 else
                    {
                    Toast.makeText(getContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return root;
    }

}

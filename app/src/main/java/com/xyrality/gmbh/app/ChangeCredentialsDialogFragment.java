package com.xyrality.gmbh.app;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by yarik on 26.09.15.
 */
public class ChangeCredentialsDialogFragment extends DialogFragment {

    public ChangeCredentialsDialogFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.change_credentials_dialog_layout, container, false);

        Button confirmChanges = (Button) view.findViewById(R.id.confirmChangeButton);

        confirmChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText loginEditText = (EditText) view.findViewById(R.id.loginEditText);
                EditText passEditText = (EditText) view.findViewById(R.id.passEditText);
                UserAccount.getInstance().setLogin(loginEditText.getText().toString());
                UserAccount.getInstance().setPassword(passEditText.getText().toString());
                ((ActivityGameWorldsList) getActivity()).startGameWorldsLoader(false);
                dismiss();
            }
        });

        Button discardChanges = (Button) view.findViewById(R.id.discardChangeButton);
        discardChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }
}

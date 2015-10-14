package com.hw.corcow.sampledialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Tacademy on 2015-10-14.
 */
public class CustomDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    EditText inputView;
    Button btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog,container);
        inputView = (EditText) view.findViewById(R.id.custom_input);
        btn = (Button) view.findViewById(R.id.custom_btn_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputView.getText().toString();
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Dialog dlg = getDialog();
        dlg.getWindow().setLayout(1000, 1000);
        // NO TITLE
        // d.setTitle("Dialog Test");


        // 위치변경
        WindowManager.LayoutParams params = dlg.getWindow().getAttributes();
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = 300;
        params.y = 400;
        dlg.getWindow().setAttributes(params);
    }
}

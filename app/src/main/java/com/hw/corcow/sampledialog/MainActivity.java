package com.hw.corcow.sampledialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAlertDialog(View v) {
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Alert Dialog");
        builder.setMessage("This is Alert Dialog");
        builder.setCancelable(false);                           // Back키 시 dismiss 막기
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    final String[] list = {"list 0", "list 1", "list 2", "list 3"};
    public void onListDialog (View v) {
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("list Dialog");
        builder.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "list " + which + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    public void onSingleChoiceDialog (View v) {
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Single Choice Dialog");
        builder.setSingleChoiceItems(list, 2, new DialogInterface.OnClickListener() {       // 두번째 인자 : Default 선택위치 (index)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "list " + which + " clicked", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void onMultipleChoiceDialog (View v) {
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Multiple Choice Dialog");
        final boolean[] mSelected = new boolean[] {false, true, true, false};
        builder.setMultiChoiceItems(list, mSelected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                mSelected[which] = isChecked;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.length; i++)
                    if (mSelected[i])
                        sb.append(list[i] + ",");
                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    Handler mHandler = new Handler(Looper.getMainLooper());

    public void onProgressSpinnerDialog(View v) {
        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setTitle("Progress Dialog Spinner");
        dialog.setMessage("progress loading...");
        dialog.show();

        mHandler.postDelayed(new Runnable() {       // 시간 지난 후 수행
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);
    }

    public void onProgressHorizontalDialog(View v) {
        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setTitle("Progress Dialog Horizontal");
        dialog.setMessage("progress loading...");

        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);               // style 추가
        dialog.setMax(150);
        dialog.show();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int progress = dialog.getProgress();
                progress += 10;
                dialog.setProgress(progress);

                if (progress < dialog.getMax()) {
                    dialog.setSecondaryProgress(progress + 10);
                } else {
                    dialog.setSecondaryProgress(dialog.getMax());
                }

                if (progress < dialog.getMax()) {
                    mHandler.postDelayed(this, 200);
                } else {
                    dialog.dismiss();
                }
            }
        }, 200);
    }

    public void onDialogFragment(View v) {
        FirstDialogFragment dialog = new FirstDialogFragment();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    public void onCustomDialog(View v) {
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");

    }
}

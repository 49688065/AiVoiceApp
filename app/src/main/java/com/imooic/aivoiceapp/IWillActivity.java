package com.imooic.aivoiceapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class IWillActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.i_will_activity);
        findViewById(R.id.i_will).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuild = new AlertDialog.Builder(IWillActivity.this);
                View dialogView1 = View.inflate(getApplicationContext(), R.layout.add_dialog, null);

                dialogBuild.setView(dialogView1);
                AlertDialog alertDialog = dialogBuild.create();
                dialogView1.findViewById(R.id.know_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        Log.e("lam", "setOnClickListener  " + alertDialog.toString());
                    }

                });
                alertDialog.show();
                alertDialog.getWindow().setLayout(900,800);

                Log.e("lam", "show  " + alertDialog.toString());

            }

        });

    }
}

package com.example.abisheks.subclient;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends AppCompatActivity {
    Button savebtn;
    Button okbtn;
    FloatingActionButton addbtn;
    Button viewbtn;
    EditText clientadd,emailadd,stateadd,countyadd,cityadd;
    TextView clienttxt,emailtxt,statetxt,countytxt,citytxt;
    Dialog AddDialog,ViewDialog;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("ClientPref", MODE_PRIVATE);
        addbtn = (FloatingActionButton) findViewById(R.id.fabsubclientAdd);
        viewbtn = (Button)findViewById(R.id.viewbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            String client,email;
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //Dialog to Get User Input
                AddDialog = new Dialog(MainActivity.this);
                AddDialog.setContentView(R.layout.add_data);
                clientadd = (EditText)AddDialog.findViewById(R.id.addclientname);
                emailadd = (EditText)AddDialog.findViewById(R.id.addemailid);
                stateadd = (EditText)AddDialog.findViewById(R.id.addstate);
                countyadd = (EditText)AddDialog.findViewById(R.id.addcounty);
                cityadd = (EditText)AddDialog.findViewById(R.id.addcity);
                savebtn = (Button)AddDialog.findViewById(R.id.saveBtn);
                savebtn.setOnClickListener(new OnClickListener(){
                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        client = clientadd.getText().toString();
                        email = emailadd.getText().toString();
                        SharedPreferences.Editor edit = pref.edit();
                        //Storing Data using SharedPreferences
                        edit.putString("Client", client);
                        edit.putString("Emailid", email);
                        edit.commit();
                        AddDialog.dismiss();
                    }
                });
                AddDialog.show();
            }
        });
        viewbtn.setOnClickListener(new View.OnClickListener() {
            String getclient,getemail,getcounty,getcity,getstate;
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //Dialog to display output
                ViewDialog = new Dialog(MainActivity.this);
                ViewDialog.setContentView(R.layout.view_fragment);
                clienttxt = (TextView)ViewDialog.findViewById(R.id.viewclientname);
                emailtxt = (TextView)ViewDialog.findViewById(R.id.viewemailid);
                statetxt = (TextView)ViewDialog.findViewById(R.id.viewstate);
                citytxt = (TextView)ViewDialog.findViewById(R.id.viewcity);
                countytxt = (TextView)ViewDialog.findViewById(R.id.viewcounty);
                okbtn = (Button)ViewDialog.findViewById(R.id.okBtn);
                //Getting Stored data from SharedPreferences
                getclient = pref.getString("Client", "");
                getemail = pref.getString("Emailid", "");
                getcounty = pref.getString("County", "");
                getcity = pref.getString("City", "");
                getstate = pref.getString("State", "");
                clienttxt.setText("Client : "+getclient);
                emailtxt.setText("Email : "+getemail);
                countytxt.setText("County : "+getemail);
                citytxt.setText("City : "+getemail);
                statetxt.setText("State : "+getemail);
                okbtn.setOnClickListener(new OnClickListener(){
                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        ViewDialog.dismiss();
                    }
                });
                ViewDialog.show();
            }
        });
    }
}

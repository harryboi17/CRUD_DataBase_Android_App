package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.datas.MyDBHandler;
import com.example.database.models.contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHandler db = new MyDBHandler(MainActivity.this);

        EditText name = (EditText)findViewById(R.id.editName);
        EditText phoneNo = (EditText) findViewById(R.id.editContact);
        EditText year = (EditText) findViewById(R.id.editYear);
        EditText branch = (EditText) findViewById(R.id.editBranch);
        Button submit = findViewById(R.id.buttonSubmit);
        Button reset = findViewById(R.id.buttonReset);
        Button show_DB = findViewById(R.id.buttonDB);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Your profile data has been submited to the DataBase", Toast.LENGTH_SHORT).show();
                contacts new_contact = new contacts();
                new_contact.setName(name.getText().toString());
                new_contact.setPhoneNumber(phoneNo.getText().toString());
                new_contact.setYear(year.getText().toString());
                new_contact.setBranch(branch.getText().toString());
                db.AddContact(new_contact);
                Reset(view);
            }
        });

        Intent intent = getIntent();
//        Log.d("DB_Harshit", "You have " + db.getCount() + " contacts in your database\n");
    }

    public void show_DataBase(View view) {
        MyDBHandler db = new MyDBHandler(MainActivity.this);
        Toast.makeText(MainActivity.this, "Welcome to My DataBase", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, DataBase.class);
        Bundle args = new Bundle();

        ArrayList<String> Cont = new ArrayList<>();
        //Fetching all Contacts
        List<contacts> AllContacts = db.getAllContacts();
        for(contacts a : AllContacts){
            Log.d("DB_Harshit", "Id: " + a.getId() + "\n"
                    + "Name: " + a.getName() + "\n"
                    + "Phone Number: " + a.getPhoneNumber() + "\n"
                    + "Year: " + a.getYear() + "\n"
                    + "Branch: " + a.getBranch() + "\n");
            Cont.add(a.getId() + " " + a.getName() + " " + a.getPhoneNumber() + " " + a.getYear() + " " + a.getBranch());
        }
        args.putSerializable("ARRAYLIST", (Serializable) Cont);
        intent.putExtra("BUNDLE", args);
        startActivity(intent);
    }

    public void Reset(View view){
        EditText name = findViewById(R.id.editName);
        EditText phoneNo = findViewById(R.id.editContact);
        EditText year = findViewById(R.id.editYear);
        EditText branch = findViewById(R.id.editBranch);

        name.getText().clear();
        phoneNo.getText().clear();
        year.getText().clear();
        branch.getText().clear();
    }
}
package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.database.datas.MyDBHandler;
import com.example.database.models.contacts;

import java.util.ArrayList;
import java.util.List;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        int i = intent.getIntExtra("idx", 0);
        MyDBHandler db = new MyDBHandler(Update.this);

        Button submit = findViewById(R.id.buttonUp);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts updt = new contacts();
                updt.setId(i);

                EditText name = findViewById(R.id.editNameUp);
                updt.setName(name.getText().toString());

                EditText phone = findViewById(R.id.editContactUp);
                updt.setPhoneNumber(phone.getText().toString());

                EditText year = findViewById(R.id.editYearUp);
                updt.setYear(year.getText().toString());

                EditText branch = findViewById(R.id.editBranchUp);
                updt.setBranch(branch.getText().toString());

                db.updateContact(updt);
                String change =  updt.getId() + " " + updt.getName() + " " + updt.getPhoneNumber() + " " + updt.getYear() + " " + updt.getBranch();

                Intent intent1 = new Intent(Update.this, MainActivity.class);
//                intent1.putExtra("NEW_STRING", change);
                startActivity(intent1);
            }
        });

    }
}
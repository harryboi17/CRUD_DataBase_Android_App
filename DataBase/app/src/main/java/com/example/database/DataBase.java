package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.datas.MyDBHandler;
import com.example.database.models.contacts;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        Button Delall = findViewById(R.id.buttonDelAll);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<String> Contacts = (ArrayList<String>)args.getSerializable("ARRAYLIST");

        listView = findViewById(R.id.listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Contacts);
        listView.setAdapter(arrayAdapter);

        MyDBHandler db = new MyDBHandler(DataBase.this);

        Delall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<contacts> AllContacts = db.getAllContacts();
                for(contacts a : AllContacts){
                    db.deleteContact(a);
                }
                listView.setAdapter(null);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                PopupMenu popupMenu = new PopupMenu(DataBase.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.getDragToOpenListener();

                Object item = adapterView.getItemAtPosition(i);
                String value = item.toString();

                int k = 0;
                int idx = 0;
                while(value.charAt(k) != '.'){
                    idx = idx*10;
                    idx += (value.charAt(k) - '0');
                    k++;
                }
                final int index = idx;

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(DataBase.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        switch (menuItem.getItemId()){
                            case R.id.delete:
                                db.deleteContactbyID(index);
                                Contacts.remove(i);
                                arrayAdapter.notifyDataSetChanged();
                                return true;
                            case R.id.reset:
                                Intent intent1 = new Intent(DataBase.this, Update.class);
                                intent1.putExtra("idx", index);
                                startActivity(intent1);
//
//                                Intent intent2 = getIntent();
//                                String updated_string = intent2.getStringExtra("NEW_STRING");
//                                Contacts.set(i, updated_string);
//                                arrayAdapter.notifyDataSetChanged();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                return true;
            }
        });

    }
}
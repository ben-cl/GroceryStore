package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.grocerystore.data.DatabaseHandler;
import com.example.grocerystore.model.Store;

public class NewStore extends AppCompatActivity {


    private EditText nameField;
    private Button addStore;
    private Button backMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_store);

        final DatabaseHandler db = new DatabaseHandler(NewStore.this);


        nameField = findViewById(R.id.nameField);
        addStore = findViewById(R.id.addStore);
        backMenu = findViewById(R.id.backMenuAddStore);

        // backMenuAddStore

        //
        addStore.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                // Add Store
                String name = nameField.getText().toString();

                db.addStore(new Store(name));


                // Launch back menu
                Intent intent = new Intent(NewStore.this, MainActivity.class);
                startActivity(intent);


            }
        });

        // Back button if does not want to add any new store
        backMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Launch back menu
                Intent intent = new Intent(NewStore.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }
}

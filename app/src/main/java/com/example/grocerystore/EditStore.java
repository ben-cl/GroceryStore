package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.grocerystore.data.DatabaseHandler;
import com.example.grocerystore.model.Store;

public class EditStore extends AppCompatActivity {

    private EditText nameEditField;
    private Button editStore;
    private Button backMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_store);

        final DatabaseHandler db = new DatabaseHandler(EditStore.this);


        nameEditField = findViewById(R.id.nameEditField);
        editStore = findViewById(R.id.editStore);
        backMenu = findViewById(R.id.backMenuEditStore);


        Intent intent = getIntent();
        final Store store = (Store)intent.getSerializableExtra("store");

        nameEditField.setText(store.getName());


        //
        editStore.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){


                // Edit Store
                String name = nameEditField.getText().toString();

                if(name.isEmpty()){
                    openDialog();
                }
                else {

                    store.setName(name);


                    //todo
                    db.editStore(store);


                    // Launch back menu
                    Intent intentback = new Intent(EditStore.this, MainActivity.class);
                    startActivity(intentback);
                }
            }
        });

        // Back button if does not want to edit any new store
        backMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Launch back menu
                Intent intent = new Intent(EditStore.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }

    public void openDialog(){
        NullDialog nullDialog = new NullDialog();
        nullDialog.show(getSupportFragmentManager(), "null dialog");
    }

}

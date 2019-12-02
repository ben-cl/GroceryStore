package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.grocerystore.data.DatabaseHandler;
import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Store;

public class NewItem extends AppCompatActivity {


    private EditText nameField;
    private Button addItem;
    private Button backMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        final DatabaseHandler db = new DatabaseHandler(NewItem.this);


        nameField = findViewById(R.id.nameItemField);
        addItem = findViewById(R.id.addItem);
        backMenu = findViewById(R.id.backMenuAddItem);


        //
        addItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){


                String name = nameField.getText().toString();

                if(name.isEmpty()){
                    openDialog();
                }
                else {

                    // Add Item

                    //TODO add foreign storeId intent bundle?

                    Intent intentSet = getIntent();

                    //int storeId = Integer.valueOf(intentSet.getStringExtra("storeId"));

                    Store store = (Store) intentSet.getSerializableExtra("store");


                    Log.d("test", "onClick: adding item fk" + store.getId());

                    //testing


                    db.addItem(new Item(store.getId(), name));


                    // Launch back menu
                    Intent intent = new Intent(NewItem.this, ItemList.class);
                    intent.putExtra("store", store);
                    startActivity(intent);
                }

            }
        });

        // Back button if does not want to add any new item
        backMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intentSet = getIntent();
                Store store = (Store)intentSet.getSerializableExtra("store");

                // Launch back menu
                Intent intent = new Intent(NewItem.this, ItemList.class);
                intent.putExtra("store", store);
                startActivity(intent);

            }
        });


    }
    public void openDialog(){
        NullDialog nullDialog = new NullDialog();
        nullDialog.show(getSupportFragmentManager(), "null dialog");
    }
}

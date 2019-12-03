package com.example.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.grocerystore.adapter.RecyclerViewAdapter;
import com.example.grocerystore.data.DatabaseHandler;
import com.example.grocerystore.model.Store;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private ArrayList<Store> storeArrayList;
    //private ArrayList<String> contacttArrayList;
    private RecyclerView recyclerView;
    public RecyclerViewAdapter recyclerViewAdapter;

    private Button addStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        storeArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        //db.addStore(new Store("McHebbens "));
        //db.addStore(new Store( "IGA"));


        List<Store> storeList = db.getAllStores();

        for(Store store: storeList) {
            Log.d("test list", "onCreate: " + store.getName());
            //Log.d("test list", "onCreate: " + contact.getPhoneNumber());
            Log.d("test list", "onCreate: " + store.getId());

            storeArrayList.add(store);


        }

        recyclerViewAdapter = new RecyclerViewAdapter(this, storeArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);


        // Add Store button feature
        addStore = findViewById(R.id.addStore);
        addStore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent( MainActivity.this, NewStore.class);
                startActivity(intent);
            } });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);


        return true;
    }

    // When option menu selected launch new store form +
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.addMenu){
            Intent intent = new Intent( MainActivity.this, NewStore.class);
            startActivity(intent);

            Toast.makeText(this, "selecte", Toast.LENGTH_SHORT).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}

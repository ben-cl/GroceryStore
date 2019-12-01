package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.grocerystore.adapter.RecyclerViewAdapter;
import com.example.grocerystore.adapter.RecyclerViewAdapterItem;
import com.example.grocerystore.data.DatabaseHandler;
import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Store;

import java.util.ArrayList;
import java.util.List;

public class ItemList extends AppCompatActivity {


    private ArrayList<Item> itemArrayList;
    //private ArrayList<String> contacttArrayList;
    private RecyclerView recyclerView;

    //Take new recycler view for items?

    private Button addItem;
    private Button backStore;


    public RecyclerViewAdapterItem recyclerViewAdapterItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);


        itemArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(ItemList.this);

        recyclerView = findViewById(R.id.recyclerViewItem);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ItemList.this));



        List<Item> itemList = db.getAllItems();

        // todo testing to get only items form right store id
        Intent intent = getIntent();
        final Store store = (Store)intent.getSerializableExtra("store");



        //In this for I should compare and take only the one in store id passed
        for(Item item: itemList) {
            Log.d("test list", "onCreate: " + item.getName());
            //Log.d("test list", "onCreate: " + contact.getPhoneNumber());
            Log.d("test list", "onCreate: " + item.getStoreId());

            if(item.getStoreId() == store.getId()) {
                itemArrayList.add(item);
            }


        }


        //Create second adapter for items!?
        recyclerViewAdapterItem = new RecyclerViewAdapterItem(this, itemArrayList);
        recyclerView.setAdapter(recyclerViewAdapterItem);

        addItem = findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                Log.d("test", "onClick: "+store.getId());

                Intent intent = new Intent( ItemList.this, NewItem.class);
                intent.putExtra("store", store);


                startActivity(intent);
            } });

        backStore = findViewById(R.id.backStore);
        backStore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent( ItemList.this, MainActivity.class);
                //intent.putExtra("store", store);

                startActivity(intent);
            } });


    }
}

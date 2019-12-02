package com.example.grocerystore.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerystore.EditItem;
import com.example.grocerystore.EditStore;
import com.example.grocerystore.ItemList;
import com.example.grocerystore.R;
import com.example.grocerystore.data.DatabaseHandler;
import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Store;

import java.util.List;

public class RecyclerViewAdapterItem extends RecyclerView.Adapter<RecyclerViewAdapterItem.ViewHolder> {

    private Context context;
    private List<Item> itemList;



    //<Store> storeList)
    public RecyclerViewAdapterItem(Context context,  List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    //Context context;

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        context = recyclerView.getContext();
//    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        // Not good should do item row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_row,parent,false);


        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final DatabaseHandler db = new DatabaseHandler(context);



        final Item item = itemList.get(position);

        //trick but to test to make sure
        final Store store = db.getStore(item.getStoreId());

        holder.name.setText(item.getName());

        holder.removeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){


                Log.d("testing", "onClick: remove button");
                // Call remove method

                //TODO add method for item
                db.deleteItem(item);




                // Launch back menu

                Intent intent = new Intent(context, ItemList.class);
                intent.putExtra("store", store);
                context.startActivity(intent);


            }
        });

        // Call edit method
        holder.editButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Intent intent = new Intent(context, EditItem.class);
                // pass store
                intent.putExtra("item", item);

                //
                intent.putExtra("store", store);

                context.startActivity(intent);


            }
        });
    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private Button removeButton;
        private Button editButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //TODO add id field xmml
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.item_name);
            removeButton = itemView.findViewById(R.id.removeItemButton);
            editButton = itemView.findViewById(R.id.editItemButton);


        }


        @Override
        public void onClick(View v) {

//            int position = getAdapterPosition();
//            Store store = itemList.get(position);
//            Log.d("Clicked", "onClick: "+position);
//
//            Intent intent = new Intent(context, ItemList.class);
//            intent.putExtra("name",store.getName());
//            //intent.putExtra("phone", contact.getPhoneNumber());
//
//            // pass id instead?
//
//
//            context.startActivity(intent);
        }


    }

}

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

import com.example.grocerystore.EditStore;
import com.example.grocerystore.ItemList;
import com.example.grocerystore.MainActivity;
import com.example.grocerystore.R;
import com.example.grocerystore.data.DatabaseHandler;
import com.example.grocerystore.model.Store;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Store> storeList;

    //<Store> storeList)
    public RecyclerViewAdapter(Context context,  List<Store> storetList) {
        this.context = context;
        this.storeList = storetList;
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


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_store_row,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final DatabaseHandler db = new DatabaseHandler(context);



        final Store store = storeList.get(position);

        holder.name.setText(store.getName());

        //Call remove method
        holder.removeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Log.d("testing", "onClick: remove button");
                db.deleteContact(store);

               // Launch back menu
               Intent intent = new Intent(context, MainActivity.class);
               context.startActivity(intent);
            }
        });


        // Call edit method
        holder.editButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Intent intent = new Intent(context, EditStore.class);
                // pass store
                intent.putExtra("store", store);

                context.startActivity(intent);


            }
        });
        //holder.phoneNumber.setText(contact.getPhoneNumber());


    }



    @Override
    public int getItemCount() {
        return storeList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private Button removeButton;
        private Button editButton;
        //private TextView phoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.store_name);
            removeButton = itemView.findViewById(R.id.removeButton);
            editButton = itemView.findViewById(R.id.editButton);
            //phoneNumber = itemView.findViewById(R.id.phone_number);


        }


        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Store store = storeList.get(position);
            Log.d("Clicked", "onClick: "+position);

            Intent intent = new Intent(context, ItemList.class);
            intent.putExtra("store",store);
            //intent.putExtra("phone", contact.getPhoneNumber());

            // pass id instead?


            context.startActivity(intent);
        }


    }

}

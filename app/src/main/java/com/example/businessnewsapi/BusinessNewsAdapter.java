package com.example.businessnewsapi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BusinessNewsAdapter  extends RecyclerView.Adapter<BusinessNewsAdapter.BusinessNewsViewHolder> {
    private Context cont;
    private ArrayList<ArticleNewsModel> busMod;

    private ItemClickListener listener ;

    private static final String TAG = "BusinessNewsAdapter";

    public BusinessNewsAdapter(Context context, ArrayList<ArticleNewsModel> busModel){
        cont = context;
        busMod = busModel;
    }
    

    @NonNull
    @Override
    public BusinessNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cont);
        View view = inflater.inflate(R.layout.businessadapter,parent,false);
        Log.d(TAG,"This does not get executed");
        return new BusinessNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessNewsViewHolder holder, int position) {
        ArticleNewsModel model = busMod.get(position);
        Log.d(TAG,"The url is "+model.getImageUrl());
        holder.bind(model);

    }

    @Override
    public int getItemCount() {
        return busMod.size();
    }

    public class BusinessNewsViewHolder extends RecyclerView.ViewHolder{

        private TextView date ;
        private TextView title ;
        private TextView author ;
        private ImageView imageView;


        public BusinessNewsViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            title = itemView.findViewById(R.id.tit);
            author = itemView.findViewById(R.id.author);
            imageView = itemView.findViewById(R.id.mage);
            Button butt = itemView.findViewById(R.id.button);

            butt.setOnClickListener(view -> {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION ){
                        listener.readMore(position);
                    }

                }
            });

        }



        private void bind(ArticleNewsModel model) {

            if (model != null) {
                title.setText(model.getTitle());
                author.setText(model.getAuthor());
                date.setText(model.getDateFormating(model.getDate()));
                Log.d(TAG, "The value of date is " + model.getDate());
//The if condition here helps to prevent the app from crashing wen the picasso runs out of url wen it gets to the end of the recycler view
                //if (!(model.getImageUrl()).isEmpty()) {
                    Picasso.get()
                            .load(model.getImageUrl())
                            .fit()
                            .placeholder(R.drawable.ic_launcher_background)
                            .centerCrop()
                            .into(imageView);
                }

                /*else {
                    Toast.makeText(cont, "You ve gottent to the end", Toast.LENGTH_LONG).show();
                    return;
                }*/
        }
    }

    public void setClicklIstener(ItemClickListener listener){
        this.listener = listener;

    }

    public interface ItemClickListener {
        void readMore(int position);
    }
}


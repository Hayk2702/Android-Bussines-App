package com.nested.Qi;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {


    private static final int REQUEST_CALL=1;
   private ArrayList<ExampleItem> mExampleList;

    private OnItemClickListener mListener;


   public interface OnItemClickListener{
       void onItemClick(int position);
   }
   public void setOnItemClickListener(OnItemClickListener listener){
       mListener=listener;
   }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public Button mapButton;
        public Button callButton;


        public ExampleViewHolder(@NonNull View itemView , final OnItemClickListener listener) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imageView);
            mTextView1=itemView.findViewById(R.id.textView);
            mTextView2=itemView.findViewById(R.id.textView2);
            mapButton=itemView.findViewById(R.id.map_button);
            callButton=itemView.findViewById(R.id.call_button);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public ExampleAdapter(ArrayList<ExampleItem> exampleList){
        mExampleList=exampleList;


    }


    @NonNull

    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
            ExampleViewHolder evh=new ExampleViewHolder(v,mListener);
            return evh;
                }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, int position) {
        final ExampleItem currentItem=mExampleList.get(position);




       Picasso.get().load(currentItem.getmImageUrl()).into(holder.mImageView);
        holder.mTextView1.setText(currentItem.getmText1());
        holder.mTextView2.setText(currentItem.getmText2());


        holder.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MapButton
                double x=currentItem.getmX();
                double y=currentItem.getmY();


                Intent intent=new Intent(v.getContext(),MapsActivity.class);
                intent.putExtra("Latitude",x);
                intent.putExtra("Longitude",y);

                    v.getContext().startActivity(intent);

                                }
        });

        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=currentItem.getmPhoneNumber();
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phone));
                if(ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity)v.getContext(),new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                }
                else {
                    v.getContext().startActivity(intent);
                }


            }

        });



    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }



}

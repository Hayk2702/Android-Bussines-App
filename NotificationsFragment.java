package com.nested.Qi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class NotificationsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    final static ArrayList<ExampleItem> exampleList=new ArrayList<>();


    public static String data;
    @SuppressLint("LongLogTag")
    @Nullable




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



       //featchData process=new featchData();
       //process.execute();

        //  Toast.makeText(getContext().getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        //  exampleList.add(new ExampleItem(R.drawable.brioche, "Cafe Brioche", "Line 2",15,20,"tel:+37455126671"));
        // exampleList.add(new ExampleItem(R.drawable.central, "Cafe Central", "Line 4",15,70,"tel:+37477200898"));

        View rootView=inflater.inflate(R.layout.fragment_notifications, null);

        mRecyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView);
        /**
        mRecyclerView = mRecyclerView.findViewById(R.id.recyclerView);
         */
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this.getActivity());
        mAdapter=new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);




        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String imageUrl=exampleList.get(position).getmImageUrl();
                String title=exampleList.get(position).getmText1();
                String description=exampleList.get(position).getmText2();
                double x=exampleList.get(position).getmX();
                double y=exampleList.get(position).getmY();
                String phone=exampleList.get(position).getmPhoneNumber();
                String alltext=exampleList.get(position).getmAllText();
                Intent intent=new Intent(getContext(),MoreInfoActivity.class);
                intent.putExtra("imageUrl",imageUrl);
                intent.putExtra("title",title);
                intent.putExtra("description",description);
                intent.putExtra("x",x);
                intent.putExtra("y",y);
                intent.putExtra("phone",phone);
                intent.putExtra("alltext",alltext);
                getContext().startActivity(intent);


            }
        });




        return rootView;
    }




}

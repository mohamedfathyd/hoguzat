package com.khalej.hoguzat.Activity;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.khalej.hoguzat.Adapter.RecyclerAdapter_stuff;
import com.khalej.hoguzat.R;
import com.khalej.hoguzat.model.Apiclient_home;
import com.khalej.hoguzat.model.apiinterface_home;
import com.khalej.hoguzat.model.content_stuff;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class staff extends Fragment {
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    private apiinterface_home apiinterface;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_stuff recyclerAdapter;
    private List<content_stuff> contactList;
    //Constructor default
    public staff(){};
    int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageTwo = inflater.inflate(R.layout.activity_staff, container, false);
        progressBar=(ProgressBar)PageTwo.findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView=(RecyclerView)PageTwo.findViewById(R.id.review);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        sharedpref = getActivity().getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        id=sharedpref.getInt("categoryId",0);
        fetchData();
        return PageTwo;
    }
   public void fetchData(){
       apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
       Call<List<content_stuff>> call = apiinterface.getStuff(id);
       call.enqueue(new Callback<List<content_stuff>>() {
           @Override
           public void onResponse(Call<List<content_stuff>> call, Response<List<content_stuff>> response) {

               try {
                   contactList = response.body();
                   progressBar.setVisibility(View.GONE);
                   recyclerAdapter=new RecyclerAdapter_stuff(getActivity(),contactList);
                   recyclerView.setAdapter(recyclerAdapter);
               }
               catch (Exception e){

               }

           }

           @Override
           public void onFailure(Call<List<content_stuff>> call, Throwable t) {

           }
       });
    }
}
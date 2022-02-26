package com.project.myapplicationj.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.myapplicationj.R;
import com.project.myapplicationj.Utils;
import com.project.myapplicationj.adapter.AdapterEmploy;
import com.project.myapplicationj.apiservices.ApiClient;
import com.project.myapplicationj.apiservices.Endpoint;

import com.project.myapplicationj.apiservices.pojos.fetch_employs.ResponseItem;
import com.project.myapplicationj.database.appdb.Appdb;
import com.project.myapplicationj.database.entities.EmployEntity;
import com.project.myapplicationj.interfac.ShowEmployDetailsInterface;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardAvtivity extends BaseActivity implements ShowEmployDetailsInterface {


    List<EmployEntity> list;
    private RecyclerView recv;
    private Appdb db;
    private AdapterEmploy adp;
    private EditText edtSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();


        if (db.getEmployEntityDao().get_count() == 0) {

            fetchDatas();

        } else {
            displayDatas("");
        }


    }

    private void init() {

        Utils.setShowEmployDetailsInterface(DashboardAvtivity.this);
        db = Appdb.getDb_instance(getApplicationContext());
        recv = findViewById(R.id.recv);
        edtSearch = findViewById(R.id.edtSearch);


        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

              String word=  editable.toString().trim();

            word=    "%"+word+"%";
                displayDatas(word);
            }
        });


    }


    private void fetchDatas() {


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<List<ResponseItem>> call = apiService.fetch_employees();

        call.enqueue(new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(Call<List<ResponseItem>> call, Response<List<ResponseItem>> response) {
                Log.d("result", "error");


                for (ResponseItem res : response.body()) {

                    String cmpName="",mob="";
                    try{
                        cmpName= res.getCompany().getName();
                    }
                    catch (Exception e)
                    {

                    }


                    try{
                        mob= res.getPhone();
                    }
                    catch (Exception e)
                    {

                    }

                    db.getEmployEntityDao().insert_employ_details(new EmployEntity(0, res.getId(), res.getName(), res.getUsername(), res.getEmail(), res.getAddress().getStreet(), res.getAddress().getSuite(), res.getAddress().getCity(), res.getAddress().getZipcode(),
                            res.getAddress().getGeo().getLat(),    res.getAddress().getGeo().getLng(), cmpName, res.getWebsite(), res.getProfileImage(),mob));
                }

                displayDatas("");


            }

            @Override
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {
                Log.d("result", "error");
            }
        });


    }

    private void displayDatas(String word) {

        recv.setAdapter(null);
        list = new ArrayList<>();

        if(word.trim().equals(""))
        {
            list.addAll(db.getEmployEntityDao().get_all_datas());
        }
        else
        {
            list.addAll(db.getEmployEntityDao().get_datas_similar(""+word));
        }



        adp = new AdapterEmploy(getApplicationContext(), list);
        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recv.setLayoutManager(lm);

        recv.setAdapter(adp);


    }


    @Override
    public void gotoDetailsScreen(Long employID) {
        Intent intent=new Intent(DashboardAvtivity.this, EmployDetailsActivity.class);
        intent.putExtra("employID",employID);

        Log.d("id",""+employID);
        startActivity(intent);

    }
}

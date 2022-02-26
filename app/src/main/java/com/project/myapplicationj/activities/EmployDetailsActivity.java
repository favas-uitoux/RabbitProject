package com.project.myapplicationj.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.project.myapplicationj.R;
import com.project.myapplicationj.database.appdb.Appdb;
import com.project.myapplicationj.database.entities.EmployEntity;

public class EmployDetailsActivity extends BaseActivity {

    private Appdb db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_details);

        init();







    }

    private void init()
    {


        Bundle extras = getIntent().getExtras();

        Long employID = extras.getLong("employID");

//        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        db = Appdb.getDb_instance(getApplicationContext());

      EmployEntity employDetails= db.getEmployEntityDao().get_datas_specif_employ(employID);

      Log.d("","");



        de.hdodenhof.circleimageview.CircleImageView iv= findViewById(R.id.iv);
        TextView txtName=findViewById(R.id.txtName);
        TextView txtUsername=findViewById(R.id.txtUsername);
        TextView txtEmail=findViewById(R.id.txtEmail);

        TextView txtAds1=findViewById(R.id.txtAds1);
        TextView txtAds2=findViewById(R.id.txtAds2);
        TextView txtAds3=findViewById(R.id.txtAds3);


        TextView txtMob=findViewById(R.id.txtMob);
//
//
        TextView txtweb=findViewById(R.id.txtweb);





        String profilepic = employDetails.getProfile_image_url();
        Glide.with(EmployDetailsActivity.this).load(profilepic)
                .sizeMultiplier(.5f)

                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(iv);

        txtName.setText(""+employDetails.getName());
        txtUsername.setText(""+employDetails.getUsername());
        txtEmail.setText(""+employDetails.getEmail());


        txtAds1.setText(""+employDetails.getStreet());
        txtAds2.setText(""+employDetails.getCity());
        txtAds3.setText(""+employDetails.getZip());


        txtMob.setText(""+employDetails.getMob());
        txtweb.setText(""+employDetails.getWeb());
        //txtweb.setText(""+employDetails.getWeb());

    }

}

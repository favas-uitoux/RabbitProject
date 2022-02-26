package com.project.myapplicationj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.project.myapplicationj.R;
import com.project.myapplicationj.Utils;
import com.project.myapplicationj.apiservices.pojos.fetch_employs.ResponseItem;
import com.project.myapplicationj.database.entities.EmployEntity;


import java.util.List;

public class AdapterEmploy extends RecyclerView.Adapter<AdapterEmploy.ViewHolderClass>  {



    Context context;
    List<EmployEntity> list;




    public AdapterEmploy(Context context, List<EmployEntity> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_row, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final EmployEntity cpr = list.get(position);












        holder.txtName.setText(cpr.getName());
        holder.txtCompany.setText(cpr.getCompany_name());

        String profilepic = cpr.getProfile_image_url();
        Glide.with(context).load(profilepic)
                .sizeMultiplier(.5f)

                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.iv);


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Utils.getShowEmployDetailsInterface().gotoDetailsScreen(cpr.getEmp_id());

            }
        });









      //  holder.txtold.setBackgroundResource(R.color.color1);







    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txtName,txtCompany;
        ImageView iv;
        CardView card;




        public ViewHolderClass(View itemView) {
            super(itemView);



            txtName = itemView.findViewById(R.id.txtName);
            txtCompany= itemView.findViewById(R.id.txtCompany);

            iv = itemView.findViewById(R.id.iv);
            card = itemView.findViewById(R.id.card);



        }
    }





}

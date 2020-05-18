package com.anemon.ykssayac.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.anemon.ykssayac.Activitys.EgzersizActivity;
import com.anemon.ykssayac.Database.LocalDatabase;
import com.anemon.ykssayac.Models.Ders;
import com.anemon.ykssayac.R;

import java.util.List;


public class DersAdapter extends  RecyclerView.Adapter<DersAdapter.MyViewHolder> {

    private List<Ders> dersList;
    private LayoutInflater inflater;
    private Context context;
    private LocalDatabase localDb;
    private AlphaAnimation clickAnimation;
    public DersAdapter(LocalDatabase localDb, Context context) {
        this.localDb=localDb;
        this.dersList = localDb.getAllDers();
        inflater = LayoutInflater.from(context);;
        this.context = context;
        clickAnimation = new AlphaAnimation(1F, 0.4F);
        clickAnimation.setDuration(200);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ders_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ders clicked = dersList.get(position);
        holder.setdata(clicked,position);
    }

    @Override
    public int getItemCount() {
        return dersList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView dersAdiTxt,cozulenTxt;
        ImageView dersImage;
        LinearLayout card;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dersAdiTxt=itemView.findViewById(R.id.tv_ders_baslik);
            dersImage=itemView.findViewById(R.id.img_ders);
            card = itemView.findViewById(R.id.card_ders);
            cozulenTxt=itemView.findViewById(R.id.tv_cozulen);
        }

        @SuppressLint("SetTextI18n")
        void setdata(Ders clicked, final int position) {
            dersAdiTxt.setText(clicked.getDersAdi());
            dersImage.setImageBitmap(clicked.getImage());
            cozulenTxt.setText(""+localDb.getCountCozulenSoru(clicked.getId()));
            card.setOnClickListener(v -> {
                v.startAnimation(clickAnimation);
                Intent intent = new Intent(context, EgzersizActivity.class);
                intent.putExtra("dersAdi",dersList.get(position).getDersAdi());
                intent.putExtra("id",dersList.get(position).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
        }
    }
}

package com.anemon.ykssayac.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.anemon.ykssayac.Models.Duyuru;
import com.anemon.ykssayac.R;


import java.util.List;

public class DuyuruAdapter  extends  RecyclerView.Adapter<DuyuruAdapter.MyViewHolder> {
    private List<Duyuru> duyuruList;
    private LayoutInflater inflater;
    private Context context;

    public DuyuruAdapter(Context context, List<Duyuru> duyuruList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.duyuruList = duyuruList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.duyuru_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Duyuru clicked = duyuruList.get(position);
            holder.setData(clicked);
    }

    @Override
    public int getItemCount() {
        return duyuruList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTwxtView, conntentTextView;
        ImageView imageView;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.rl_duyuru);
            titleTwxtView = itemView.findViewById(R.id.baslik);
            conntentTextView = itemView.findViewById(R.id.textduyuru);
            imageView = itemView.findViewById(R.id.duyuru_item_image);
        }

        public void setData(Duyuru clicked) {
            this.titleTwxtView.setText(clicked.getTitle());
            this.conntentTextView.setText(clicked.getContent());
            this.imageView.setImageBitmap(clicked.getImage());
            if (clicked.getUrl().length()>5) {
                this.cardView.setOnClickListener(v -> {
                    try {
                        String url = clicked.getUrl();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }


}

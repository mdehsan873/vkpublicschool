package com.myjre.javed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class topadapter extends RecyclerView.Adapter<topadapter.myholder> {
    private Context context;
    private List<topupload> topuploaded;

    public topadapter(Context context, List<com.myjre.javed.topupload> topuploaded) {
        this.context = context;
        this.topuploaded = topuploaded;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topstudentshow, parent, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        topadapter.myholder viewHolder = new topadapter.myholder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, int position) {
        topupload topupload=topuploaded.get(position);
        holder.names.setText(topupload.getName());
        holder.clases.setText(topupload.getClasses());
        String url;
        url= topupload.getImage();
        Picasso.get().load(url).into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return topuploaded.size();
    }

    public class myholder extends RecyclerView.ViewHolder {
        ImageView pic;
        TextView names,clases;
        public myholder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.stdtopimg);
            names=itemView.findViewById(R.id.stdtopname);
            clases=itemView.findViewById(R.id.stdtopclass);
        }
    }
}

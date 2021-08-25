package com.myjre.javed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class facadapter extends RecyclerView.Adapter<facadapter.holder> {
   private Context context;
   private List<upfac> upfacs;

    public facadapter(Context context, List<upfac> upfacs) {
        this.context = context;
        this.upfacs = upfacs;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.showfac,parent,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        facadapter.holder viewHolder = new facadapter.holder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        upfac upfac=upfacs.get(position);
        holder.name.setText(upfac.getName());
        holder.qual.setText(upfac.getQualificaion());
        holder.post.setText(upfac.getPost());
        String url;
        url=upfac.getImage();
        Picasso.get().load(url).into(holder.facimgage);

    }

    @Override
    public int getItemCount() {
        return upfacs.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        ImageView facimgage;
        TextView name,qual,post;
        public holder(@NonNull View itemView) {
            super(itemView);
            facimgage=itemView.findViewById(R.id.facimg);
            name=itemView.findViewById(R.id.facname);
            qual=itemView.findViewById(R.id.facqua);
            post=itemView.findViewById(R.id.facpost);
        }
    }
}

package com.myjre.javed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class notiadapter extends RecyclerView.Adapter<notiadapter.notiholder> {
    List<notidata> notidata;
    Context context;

    public notiadapter(Context context,List<com.myjre.javed.notidata> notidata) {
        this.notidata = notidata;
        this.context = context;
    }

    @Override
    public notiholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewnotification, parent, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        notiholder viewHolder = new notiholder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(notiholder holder, int position) {
        notidata notidata1= notidata.get(position);
        holder.textView.setText(notidata1.getNotification());
    }

    @Override
    public int getItemCount() {
        return notidata.size();
    }

    public class notiholder extends RecyclerView.ViewHolder {
        TextView textView;
        public notiholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.notitext);
        }
    }
}

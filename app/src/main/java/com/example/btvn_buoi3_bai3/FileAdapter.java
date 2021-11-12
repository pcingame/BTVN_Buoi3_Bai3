package com.example.btvn_buoi3_bai3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.MyViewHolder>{

    private List<File> fileList;
    private IClickItemFile iClickItemFile;

    public FileAdapter(IClickItemFile iClickItemFile) {
        this.iClickItemFile = iClickItemFile;
    }

    public interface IClickItemFile {
        void updateFileName(String name);
        void deleteFile(File file);
    }

    public void setData(List<File> list){
        this.fileList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  FileAdapter.MyViewHolder holder, int position) {
        final File file = fileList.get(position);
        if (file == null){
            return;
        }
        holder.tvFile.setText(file.getName() + "");
        holder.imgDeleteFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemFile.deleteFile(file);
            }
        });

        holder.imgUpdateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemFile.updateFileName(file.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(fileList != null){
            return fileList.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private FileAdapter adapter;
        private TextView tvFile;
        private ImageView imgUpdateName;
        private ImageView imgDeleteFolder;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            tvFile = itemView.findViewById(R.id.tvName);
            imgDeleteFolder = itemView.findViewById(R.id.deleteFoler);
            imgUpdateName = itemView.findViewById(R.id.deleteName);

        }

    }


}

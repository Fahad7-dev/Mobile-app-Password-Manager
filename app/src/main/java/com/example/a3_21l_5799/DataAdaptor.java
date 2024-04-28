package com.example.a3_21l_5799;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdaptor extends RecyclerView.Adapter<DataAdaptor.Viewholder>{

    ArrayList<Data> vault;
    Context context;
    AppCompatActivity parentActivity;


    DataAdaptor(ArrayList<Data> vault,Context c,AppCompatActivity parentActivity){
        this.vault=vault;
        this.context=c;
        this.parentActivity=parentActivity;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_datavault,parent,false);
        return new Viewholder(v);    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {


        if (!(parentActivity instanceof MainActivity)) {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            databaseHelper.open();
            ArrayList<Data> deletedData = databaseHelper.getAllDeletedData();
            databaseHelper.close();

            vault.addAll(deletedData);

            notifyDataSetChanged();
        }
        holder.tvName.setText(vault.get(position).getUsername());
        holder.tvPass.setText(vault.get(position).getPassword());
        holder.tvUrl.setText(vault.get(position).getUrl());


            if (!(parentActivity instanceof MainActivity)) {

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context);
                    deleteDialog.setTitle("Confirmation");
                    deleteDialog.setMessage("Do you want to delete it or restore it?");
                    deleteDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            DatabaseHelper database = new DatabaseHelper(context);
                            database.open();
                            int Delete = vault.get(holder.getAdapterPosition()).getId();
                            database.deleteVaultPermanently(vault.get(holder.getAdapterPosition()).getId());
                            database.close();

                            vault.remove(holder.getAdapterPosition());

                            notifyItemRemoved(holder.getAdapterPosition());
                            Toast.makeText(context, "Password deleted", Toast.LENGTH_SHORT).show();
                        }
                    });
                    deleteDialog.setNegativeButton("Restore", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    return false;
                }
            });
        }


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog editDialog = new AlertDialog.Builder(context).create();
                View view = LayoutInflater.from(context).inflate(R.layout.edit, null, false);
                editDialog.setView(view);

                EditText etNameEdit = view.findViewById(R.id.etNameEdit);
                EditText etPassEdit = view.findViewById(R.id.etPassEdit);
                EditText etUrlEdit = view.findViewById(R.id.etUrlEdit);

                Button btnEditDetails = view.findViewById(R.id.btnEditDetails);

                etNameEdit.setText(vault.get(holder.getAdapterPosition()).getUsername());
                etPassEdit.setText(vault.get(holder.getAdapterPosition()).getPassword());
                etUrlEdit.setText(vault.get(holder.getAdapterPosition()).getUrl());


                editDialog.show();


                btnEditDetails.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(View v) {
                        String name = etNameEdit.getText().toString().trim();
                        String pass = etPassEdit.getText().toString();
                        String url = etUrlEdit.getText().toString();

                        DatabaseHelper myDatabaseHelper = new DatabaseHelper(context);
                        myDatabaseHelper.open();
                        myDatabaseHelper.update(vault.get(holder.getAdapterPosition()).getId(),name, pass,url);
                        myDatabaseHelper.close();

                        editDialog.dismiss();

                        vault.get(holder.getAdapterPosition()).setUsername(name);
                        vault.get(holder.getAdapterPosition()).setPassword(pass);
                        vault.get(holder.getAdapterPosition()).setUrl(url);

                        notifyDataSetChanged();

                    }
                });
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context);
                deleteDialog.setTitle("Confirmation");
                deleteDialog.setMessage("Do you really want to delete it?");
                deleteDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        DatabaseHelper database = new DatabaseHelper(context);
                        database.open();
                        int Delete = vault.get(holder.getAdapterPosition()).getId();
                        database.deleteVaultData(vault.get(holder.getAdapterPosition()).getId());
                        database.close();

                        vault.remove(holder.getAdapterPosition());

                        notifyItemRemoved(holder.getAdapterPosition());
                        Toast.makeText(context, "Password deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                deleteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                deleteDialog.show();

                return false;
            }
        });
    }
    @Override
    public int getItemCount() {
        return vault.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView tvName,tvPass,tvUrl;
        Button btnEdit;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvPass=itemView.findViewById(R.id.tvPass);
            tvUrl=itemView.findViewById(R.id.tvUrl);

            btnEdit=itemView.findViewById(R.id.btnEdit);

        }
    }
}

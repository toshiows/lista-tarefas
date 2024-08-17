package com.example.controlefinanceiro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlefinanceiro.R;
import com.example.controlefinanceiro.model.Receita;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AdapterReceita extends RecyclerView.Adapter<AdapterReceita.MyViewHolder> {
    private List<Receita> listaReceitas;
    private Context context;
    private SimpleDateFormat dateFormat;

    public AdapterReceita(List<Receita> listaReceitas) {
        this.listaReceitas = listaReceitas;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    }

    @NonNull
    @Override
    public AdapterReceita.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_receita,parent,false);
        return new AdapterReceita.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReceita.MyViewHolder holder, int position) {
        Receita Receita = listaReceitas.get(position);
        holder.descricaoReceita.setText(Receita.getDescricao());
        holder.valorReceita.setText(String.format(Locale.getDefault(), "$%.2f", Receita.getValor()));
        // Formata a data antes de defini-la no TextView
        holder.dataReceita.setText(dateFormat.format(Receita.getData()));
    }

    @Override
    public int getItemCount() {
        return listaReceitas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView descricaoReceita;
        TextView valorReceita;
        TextView dataReceita;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            descricaoReceita = itemView.findViewById(R.id.lblDescricaoReceita);
            valorReceita = itemView.findViewById(R.id.lblValorReceita);
            dataReceita = itemView.findViewById(R.id.lblDataReceita);
        }
    }
}

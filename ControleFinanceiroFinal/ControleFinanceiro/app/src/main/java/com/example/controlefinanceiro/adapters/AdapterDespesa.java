package com.example.controlefinanceiro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlefinanceiro.R;
import com.example.controlefinanceiro.model.Despesa;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AdapterDespesa extends RecyclerView.Adapter<AdapterDespesa.MyViewHolder> {
    private List<Despesa> listaDespesas;
    private Context context;
    private SimpleDateFormat dateFormat;

    public AdapterDespesa(List<Despesa> listaDespesas) {
        this.listaDespesas = listaDespesas;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_despesa,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Despesa despesa = listaDespesas.get(position);
        holder.descricaoDespesa.setText(despesa.getDescricao());
        holder.valorDespesa.setText(String.format(Locale.getDefault(), "$%.2f", despesa.getValor()));
        // Formata a data antes de defini-la no TextView
        holder.dataDespesa.setText(dateFormat.format(despesa.getData()));
    }

    @Override
    public int getItemCount() {
        return listaDespesas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView descricaoDespesa;
        TextView valorDespesa;
        TextView dataDespesa;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            descricaoDespesa = itemView.findViewById(R.id.lblDescricaoDespesa);
            valorDespesa = itemView.findViewById(R.id.lblValorDespesa);
            dataDespesa = itemView.findViewById(R.id.lblDataDespesa);
        }
    }
}


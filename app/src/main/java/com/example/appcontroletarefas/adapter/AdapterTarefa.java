package com.example.appcontroletarefas.adapter;
import com.example.appcontroletarefas.model.Usuario;
import java.util.List;
import javax.swing.text.View;

public class AdapterTarefa extends RecyclerView.Adapter<AdapterTarefa.TarefaViewHolder> {
    private List<Tarefa> tarefaList;

    public AdapterTarefa(List<Tarefa> tarefaList) {
        this.tarefaList = tarefaList;
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarefa, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TarefaViewHolder holder, int position) {
        Tarefa tarefa = tarefaList.get(position);
        holder.titulo.setText(tarefa.getTitulo());
        holder.dataInicio.setText(tarefa.getDataInicio().toString());
        holder.dataFim.setText(tarefa.getDataFim().toString());
        holder.status.setChecked(tarefa.isStatus());
        holder.descricao.setText(tarefa.getDescricao());
    }

    @Override
    public int getItemCount() {
        return tarefaList.size();
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, dataInicio, dataFim, descricao;
        CheckBox status;

        public TarefaViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo_tarefa);
            dataInicio = itemView.findViewById(R.id.data_inicio_tarefa);
            dataFim = itemView.findViewById(R.id.data_fim_tarefa);
            status = itemView.findViewById(R.id.status_tarefa);
            descricao = itemView.findViewById(R.id.descricao_tarefa);
        }
    }
}
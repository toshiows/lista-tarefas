package com.example.appcontroletarefas.adapter;

public class AdapterSubtarefa  extends RecyclerView.Adapter<AdapterSubtarefa.SubtarefaViewHolder> {
    private List<Subtarefa> subtarefaList;

    public AdapterSubtarefa(List<Subtarefa> subtarefaList) {
        this.subtarefaList = subtarefaList;
    }

    @Override
    public SubtarefaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subtarefa, parent, false);
        return new SubtarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubtarefaViewHolder holder, int position) {
        Subtarefa subtarefa = subtarefaList.get(position);
        holder.prioridade.setText(subtarefa.getPrioridade());
        holder.status.setChecked(subtarefa.isStatus());
    }

    @Override
    public int getItemCount() {
        return subtarefaList.size();
    }

    class SubtarefaViewHolder extends RecyclerView.ViewHolder {
        TextView prioridade;
        CheckBox status;

        public SubtarefaViewHolder(View itemView) {
            super(itemView);
            prioridade = itemView.findViewById(R.id.prioridade_subtarefa);
            status = itemView.findViewById(R.id.status_subtarefa);
        }
    }
}


package com.example.appcontroletarefas.adapter;

public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.CategoriaViewHolder>{

        private List<Categoria> categoriaList;

        public AdapterCategoria(List<Categoria> categoriaList) {
            this.categoriaList = categoriaList;
        }

        @Override
        public CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
            return new CategoriaViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CategoriaViewHolder holder, int position) {
            Categoria categoria = categoriaList.get(position);
            holder.prioridade.setText(categoria.getPrioridade());
        }

        @Override
        public int getItemCount() {
            return categoriaList.size();
        }

        class CategoriaViewHolder extends RecyclerView.ViewHolder {
            TextView prioridade;

            public CategoriaViewHolder(View itemView) {
                super(itemView);
                prioridade = itemView.findViewById(R.id.prioridade_categoria);
            }
        }
    }


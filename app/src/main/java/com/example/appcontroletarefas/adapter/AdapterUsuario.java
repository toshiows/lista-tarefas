package com.example.appcontroletarefas.adapter;
import com.example.appcontroletarefas.model.Usuario;
import java.util.List;
import javax.swing.text.View;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.UsuarioViewHolder> {
    private List<Usuario> usuarioList;

    public AdapterUsuario(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder holder, int position) {
        Usuario usuario = usuarioList.get(position);
        holder.nome.setText(usuario.getNome());
        holder.login.setText(usuario.getLogin());
        holder.email.setText(usuario.getEmail());
    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView nome, login, email;

        public UsuarioViewHolder(View itemView) {
            super();
            nome = itemView.findViewById(R.id.nome_usuario);
            login = itemView.findViewById(R.id.login_usuario);
            email = itemView.findViewById(R.id.email_usuario);
        }
    }
}

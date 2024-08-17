package com.example.controlefinanceiro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.controlefinanceiro.model.Receita;
import com.example.controlefinanceiro.persistencia.ReceitaDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReceitaActivity extends AppCompatActivity {
    EditText edtDescricaoReceita,edtValorReceita, edtDataReceita;
    Button btnSalvarReceita;
    private ReceitaDAO receitaDAO;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        edtDescricaoReceita = findViewById(R.id.txtDescricaoReceita);
        edtValorReceita = findViewById(R.id.txtValorReceita);
        edtDataReceita = findViewById(R.id.txtDataReceita);
        btnSalvarReceita = findViewById(R.id.cmdSalvarReceita);
        receitaDAO = new ReceitaDAO(this);
        btnSalvarReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarReceita();
            }
        });
    }

    private void salvarReceita() {
        String descricao = edtDescricaoReceita.getText().toString();
        String valorStr = edtValorReceita.getText().toString();
        String dataStr = edtDataReceita.getText().toString();
        double valor = Double.parseDouble(valorStr);
        Date data;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
            data = dateFormat.parse(dataStr);
        } catch (Exception e) {
            Toast.makeText(this, "Formato de data inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        Receita receita = new Receita();
        receita.setDescricao(descricao);
        receita.setValor(valor);
        receita.setData(data);


        boolean sucesso = receitaDAO.salvar(receita);
        if (sucesso) {
            Toast.makeText(this, "Receita salva com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro ao salvar a Receita", Toast.LENGTH_SHORT).show();
        }
    }
}
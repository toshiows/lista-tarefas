package com.example.controlefinanceiro;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlefinanceiro.adapters.AdapterDespesa;
import com.example.controlefinanceiro.adapters.AdapterReceita;
import com.example.controlefinanceiro.model.Despesa;
import com.example.controlefinanceiro.model.Receita;
import com.example.controlefinanceiro.persistencia.DespesaDAO;
import com.example.controlefinanceiro.persistencia.ReceitaDAO;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView totalExpenses, totalRevenues, netTotal;
    private TabLayout tabLayout;
    private RecyclerView recyclerDespesas, recyclerReceitas;
    private Button btnNovaDespesa, btnNovaReceita;
    private AdapterDespesa despesaAdapter;
    private AdapterReceita receitaAdapter;
    private Despesa despesaSelecionado;
    private Receita receitaSelecionado;
    private List<Despesa> listaDespesas= new ArrayList<>();
    private List<Receita> listaReceitas= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar elementos da interface
        totalExpenses = findViewById(R.id.total_expenses);
        totalRevenues = findViewById(R.id.total_revenues);
        netTotal = findViewById(R.id.net_total);
        tabLayout = findViewById(R.id.tab_layout);
        recyclerDespesas = findViewById(R.id.rvListaDespesas);
        recyclerReceitas = findViewById(R.id.rvListaReceitas);
        btnNovaDespesa = findViewById(R.id.cmdNovaDespesa);
        btnNovaReceita = findViewById(R.id.cmdNovaReceita);

        // Configurar RecyclerViews
        //RecyclerView Despesas
        recyclerDespesas.setLayoutManager(new LinearLayoutManager(this));
        recyclerDespesas.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerDespesas, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                despesaSelecionado = listaDespesas.get(position);
                Intent intencao = new Intent(MainActivity.this, DespesaActivity.class);
                intencao.putExtra("contatoSelecionado",despesaSelecionado);
                startActivity(intencao);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar o contato que será excluído
                despesaSelecionado = listaDespesas.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                //configurar o AlertDialog.Builder
                dialog.setTitle("Confirmar a exclusão");
                dialog.setMessage("Deseja excluir o contato:" + despesaSelecionado.getDescricao()+"?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DespesaDAO despesaDAO = new DespesaDAO(getApplicationContext());
                        if(despesaDAO.deletar(despesaSelecionado)){
                            CarregarDespesas();
                            Toast.makeText(MainActivity.this, "Sucesso ao excluir o registro", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Erro ao excluir o registro", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.setNegativeButton("Não",null);
                dialog.create();
                dialog.show();

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
        //REcyclerView Receitas
        recyclerReceitas.setLayoutManager(new LinearLayoutManager(this));
        recyclerReceitas.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerReceitas, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                receitaSelecionado= listaReceitas.get(position);
                Intent intencao = new Intent(MainActivity.this, DespesaActivity.class);
                intencao.putExtra("contatoSelecionado",receitaSelecionado);
                startActivity(intencao);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar o contato que será excluído
                receitaSelecionado = listaReceitas.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                //configurar o AlertDialog.Builder
                dialog.setTitle("Confirmar a exclusão");
                dialog.setMessage("Deseja excluir o contato:" + receitaSelecionado.getDescricao()+"?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ReceitaDAO receitaDAO = new ReceitaDAO(getApplicationContext());
                        if(receitaDAO.deletar(receitaSelecionado)){
                            CarregarDespesas();
                            Toast.makeText(MainActivity.this, "Sucesso ao excluir o registro", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Erro ao excluir o registro", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.setNegativeButton("Não",null);
                dialog.create();
                dialog.show();

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
        // Configurar TabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    recyclerDespesas.setVisibility(View.VISIBLE);
                    recyclerReceitas.setVisibility(View.GONE);
                    btnNovaDespesa.setVisibility(View.VISIBLE);
                    btnNovaReceita.setVisibility(View.GONE);
                    CarregarDespesas();
                } else {
                    recyclerDespesas.setVisibility(View.GONE);
                    recyclerReceitas.setVisibility(View.VISIBLE);
                    btnNovaDespesa.setVisibility(View.GONE);
                    btnNovaReceita.setVisibility(View.VISIBLE);
                    CarregarReceitas();
                }
                updateSummary(); // Atualizar o resumo financeiro
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Não é necessário fazer nada
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Não é necessário fazer nada
            }
        });

        // Adicionar abas
        tabLayout.addTab(tabLayout.newTab().setText("Despesas"));
        tabLayout.addTab(tabLayout.newTab().setText("Receitas"));

        // Inicializar o resumo financeiro
        updateSummary();

        // Carregar dados iniciais
        CarregarDespesas();
        CarregarReceitas();

        // Configurar ações dos botões
        btnNovaDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DespesaActivity.class);
                startActivity(intent);
            }
        });

        btnNovaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReceitaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CarregarDespesas() {
        DespesaDAO despesaDAO = new DespesaDAO(this);
        listaDespesas = despesaDAO.listarTodos();
        Log.d("Carregar dados", "Quantidade de registro " + listaDespesas.size());
        //Configurar o meu adapter
        despesaAdapter= new AdapterDespesa(listaDespesas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerDespesas.setLayoutManager(layoutManager);
        recyclerDespesas.setHasFixedSize(true);
        recyclerDespesas.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerDespesas.setAdapter(despesaAdapter);
    }

    private void CarregarReceitas() {
        ReceitaDAO receitaDAO = new ReceitaDAO(this);
        listaReceitas = receitaDAO.listarTodos();
        Log.d("Carregar dados receitas", "Quantidade de registro " + listaReceitas.size());
        //Configurar o meu adapter
        receitaAdapter= new AdapterReceita(listaReceitas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerReceitas.setLayoutManager(layoutManager);
        recyclerReceitas.setHasFixedSize(true);
        recyclerReceitas.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerReceitas.setAdapter(receitaAdapter);
    }

    private void updateSummary() {
        DespesaDAO despesaDAO = new DespesaDAO(this);
        ReceitaDAO receitaDAO = new ReceitaDAO(this);

        double totalExpensesValue = 0.0;
        double totalRevenuesValue = 0.0;

        for (Despesa despesa : despesaDAO.listarTodos()) {
            totalExpensesValue += despesa.getValor();
        }

        for (Receita receita : receitaDAO.listarTodos()) {
            totalRevenuesValue += receita.getValor();
        }

        double balance = totalRevenuesValue - totalExpensesValue;

        // Atualiza os TextViews
        totalExpenses.setText(String.format("Total Despesas: R$ %.2f", totalExpensesValue));
        totalRevenues.setText(String.format("Total Receitas: R$ %.2f", totalRevenuesValue));
        netTotal.setText(String.format("Valor Líquido: R$ %.2f", balance));
    }

    @Override
    protected void onStart() {
        super.onStart();
        CarregarDespesas();
        CarregarReceitas();
    }
}
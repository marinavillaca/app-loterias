// @Author: Marina Laura Villaça e Melo

package com.example.apploterias;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "App Inicializando!!");

        final TextView lblHello = findViewById(R.id.lbl_hello);
        final TextView lblResultado = findViewById(R.id.txt_resultado);
        final EditText txtQtdJogos = findViewById(R.id.txt_1);
        final EditText txtQtdNumeros = findViewById(R.id.txt_2);

        Button btnEnviar1 = findViewById(R.id.btn_enviar_1);
        Button btnEnviar2 = findViewById(R.id.btn_enviar_2);
        Button btnGerarJogos = findViewById(R.id.btn_enviar_3);

        // Configuração do primeiro botão
            btnEnviar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qtdJogosStr = txtQtdJogos.getText().toString();

                if (qtdJogosStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencha o campo de quantidade de jogos.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Toast.makeText(MainActivity.this, "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configuração do segundo botão
            btnEnviar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qtdNumerosStr = txtQtdNumeros.getText().toString().trim();

                if (qtdNumerosStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencha o campo de quantidade de números!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Toast.makeText(MainActivity.this, "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                }

                int qtdNumeros;
                try {
                    qtdNumeros = Integer.parseInt(qtdNumerosStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Digite um número válido!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (qtdNumeros < 6 || qtdNumeros > 20) {
                    Toast.makeText(MainActivity.this, "Escolha entre 6 e 20 números!", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        // Configuração do botão para gerar jogos
        btnGerarJogos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gerarJogos(txtQtdJogos, txtQtdNumeros, lblResultado);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void gerarJogos(EditText txtQtdJogos, EditText txtQtdNumeros, TextView lblResultado) {
        String qtdJogosStr = txtQtdJogos.getText().toString().trim();
        String qtdNumerosStr = txtQtdNumeros.getText().toString().trim();

        if (qtdJogosStr.isEmpty() || qtdNumerosStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        int qtdJogos, qtdNumeros;

        try {
            qtdJogos = Integer.parseInt(qtdJogosStr);
            qtdNumeros = Integer.parseInt(qtdNumerosStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Digite números válidos!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (qtdJogos <= 0) {
            Toast.makeText(this, "A quantidade de jogos deve ser maior que zero!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (qtdNumeros < 6 || qtdNumeros > 20) {
            Toast.makeText(this, "Escolha entre 6 e 20 números!", Toast.LENGTH_SHORT).show();
            return;
        }

        double precoPorJogo = calcularPreco(qtdNumeros);
        double valorTotal = qtdJogos * precoPorJogo;

        StringBuilder resultado = new StringBuilder();
        resultado.append("Quantidade de jogos: ").append(qtdJogos).append("\n");
        resultado.append("Quantidade de números por jogo: ").append(qtdNumeros).append("\n\n");

        Random random = new Random();

        for (int i = 0; i < qtdJogos; i++) {
            Set<Integer> jogo = new HashSet<>();
            while (jogo.size() < qtdNumeros) {
                jogo.add(random.nextInt(60) + 1);
            }

            ArrayList<Integer> jogoOrdenado = new ArrayList<>(jogo);
            Collections.sort(jogoOrdenado);

            resultado.append("Jogo ").append(i + 1).append(": ").append(jogoOrdenado).append("\n");
        }

        lblResultado.setText(resultado.toString());

        String mensagem = String.format("Valor total: R$ %.2f", valorTotal);
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    private double calcularPreco(int qtdNumeros) {
        switch (qtdNumeros) {
            case 6: return 5.00;
            case 7: return 35.00;
            case 8: return 140.00;
            case 9: return 420.00;
            case 10: return 1050.00;
            case 11: return 2310.00;
            case 12: return 4620.00;
            case 13: return 8580.00;
            case 14: return 15015.00;
            case 15: return 25025.00;
            case 16: return 40040.00;
            case 17: return 61880.00;
            case 18: return 92820.00;
            case 19: return 139536.00;
            case 20: return 205380.00;
            default: return 0;
        }
    }
}

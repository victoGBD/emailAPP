package dicberner.victor.emailapp;

import androidx.appcompat.app.AppCompatActivity;  // Importa classes necessárias para o Android
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Define o layout da atividade

        // Obtém referência para o botão "Enviar" no layout
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);

        // Define a ação do clique do botão Enviar
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtém dados digitados pelo usuário nos campos de e-mail, assunto e texto
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                // Cria um Intent para enviar o e-mail
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));

                // Define os dados extras no Intent
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                try {
                    // Inicia a atividade de envio de e-mail, permitindo que o usuário escolha o aplicativo
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                } catch (ActivityNotFoundException e) {
                    // Captura exceção se nenhum app de e-mail estiver disponível e exibe uma mensagem
                    Toast.makeText(MainActivity.this, "Não há nenhum app que possa realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

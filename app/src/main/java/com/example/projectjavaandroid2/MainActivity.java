package com.example.projectjavaandroid2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projectjavaandroid2.activities.UcsActivity;
import com.example.projectjavaandroid2.dto.AlunoPartialDTO;
import com.example.projectjavaandroid2.dto.Converter;
import com.example.projectjavaandroid2.helper.Response;
import com.example.projectjavaandroid2.helper.Utils;
import com.example.projectjavaandroid2.model.partial.AlunoPartial;
import com.example.projectjavaandroid2.network.GenericGetAsyncTask;
import com.example.projectjavaandroid2.network.HttpStatusCode;
import com.example.projectjavaandroid2.xml.XmlHandler;

public class MainActivity extends AppCompatActivity {

    EditText editText, editText1;
    private Button button;
    private ProgressBar pb;
    private AlunoPartial aluno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Define o layout da activity.
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Obtêm o edit text onde o utilizador insere o númnero de aluno.
        editText = findViewById(R.id.username);
        //Obtêm o edit text onde o utilizador insere a password.
        editText1 = findViewById(R.id.password);
        //Obtêm o botão onde o utilizador confirma os dados.
        button = findViewById(R.id.btok);
        //Obtêm a progress bar.
        pb = (ProgressBar) findViewById(R.id.progressBar);
        //Torna a progress bar invisivel.
        pb.setVisibility(ProgressBar.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exceptionMessage = "";
                boolean exception = false;

                try {
                    Long numAluno = Long.parseLong(editText.getText().toString());
                    Long senha = Long.parseLong(editText1.getText().toString());
                    //Obtêm o aluno inserido.
                    getAlunoFromWS(numAluno, senha);

                    //Caso não consiga começar a a general activity.
                }catch (Exception e){
                    //Obtêm a mensagem de erro.
                    exceptionMessage = e.getMessage();
                    exception = true;
                }

                if(exception) {
                    //Mostra a mensagem de erro num toast.
                    Toast.makeText(MainActivity.this,exceptionMessage,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getAlunoFromWS(Long numAluno, Long senha) {
        GenericGetAsyncTask task = new GenericGetAsyncTask(pb, this) {
            protected Response getResponseObject(String httpResponse) {
                AlunoPartialDTO alunoDTO = XmlHandler.deSerializeXML2PessoaDTO(httpResponse);
                AlunoPartial aluno = Converter.convertAlunoPartialDTO(alunoDTO);
                return new Response(HttpStatusCode.OK, aluno);
            }

            protected void onPostExecuteProcessDataUI(Object object) {
                if (object instanceof AlunoPartial) {
                    aluno = (AlunoPartial) object;
                    //Cria um intent que permite a passagem para a GeneralActivity.
                    Intent intent = new Intent(MainActivity.this, UcsActivity.class);
                    intent.putExtra("numAluno", aluno.getNumAluno());
                    intent.putExtra("senha", aluno.getSenha());
                    //Começa a GeneralActivity.
                    startActivity(intent);
                }
            }
        };

        String address = Utils.getWSAddress(this);
        task.execute(address + "/login/" + numAluno + "/" + senha);
    }


}
package com.example.projectjavaandroid2.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectjavaandroid2.R;
import com.example.projectjavaandroid2.dto.AvaliacaoDTO;
import com.example.projectjavaandroid2.dto.Converter;
import com.example.projectjavaandroid2.helper.Response;
import com.example.projectjavaandroid2.helper.Utils;
import com.example.projectjavaandroid2.model.Avaliacao;
import com.example.projectjavaandroid2.network.GenericDeleteAsyncTask;
import com.example.projectjavaandroid2.network.GenericGetAsyncTask;
import com.example.projectjavaandroid2.network.HttpStatusCode;
import com.example.projectjavaandroid2.xml.XmlHandler;

public class DeleteActivity extends AppCompatActivity {

    private Avaliacao avaliacao;

    private TextView etTipo, etData, etObs;
    private Button btOp, btCancel;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        avaliacao = null;
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.INVISIBLE);
        etTipo = (TextView) findViewById(R.id.etTipo);
        etData = (TextView) findViewById(R.id.etData);
        etObs = (TextView) findViewById(R.id.etObs);

        btOp = (Button)findViewById(R.id.btOp);
        btOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                deleteAvaliacaoInsideWS();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        btCancel = (Button)findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        });

        getAvaliacaoFromWs();
    }

    public void getAvaliacaoFromWs() {

        //Cria um thread secundária.
        GenericGetAsyncTask task = new GenericGetAsyncTask(pb, this) {
            protected Response getResponseObject(String httpResponse) {
                AvaliacaoDTO avaliacaoDTO = XmlHandler.deSerializeXML2AvaliacaoDTO(httpResponse);
                Avaliacao avaliacao = Converter.convertAvaliacaoDTO(avaliacaoDTO);
                return new Response(HttpStatusCode.OK, avaliacao);
            }

            protected void onPostExecuteProcessDataUI(Object object) {
                if (object instanceof Avaliacao) {
                    avaliacao = (Avaliacao) object;
                } else {
                    avaliacao = null;
                }
                setUi(avaliacao);
            }
        };

        //Obtêm o endereço.
        String address = Utils.getWSAddress(this);
        task.execute(address + "/getavaliacao/"
                + getIntent().getLongExtra("numAluno", 0)
                + "/" + getIntent().getLongExtra("senha", 1)
                + '/' + getIntent().getIntExtra("keyAvaliacao", 2));
    }

    public void setUi(Avaliacao avaliacao) {
        etTipo.setText(avaliacao.getTipo().toString());
        etData.setText(avaliacao.getDataAvaliacao().toString());
        etObs.setText(avaliacao.getObservacao());
    }

    public void deleteAvaliacaoInsideWS() {

        GenericDeleteAsyncTask task = new GenericDeleteAsyncTask(pb, this) {
            protected void onPostExecuteProcessDataUI(Object object) {
                Toast.makeText(DeleteActivity.this, "Avaliação eliminada com sucesso.", Toast.LENGTH_LONG).show();
            }
        };
        String address = Utils.getWSAddress(this);
        task.execute(address + "/deleteavaliacao/"
                + getIntent().getLongExtra("numAluno", 0)
        + '/' + getIntent().getLongExtra("senha", 1)
        + '/' + getIntent().getIntExtra("keyAvaliacao", 2));
    }
}

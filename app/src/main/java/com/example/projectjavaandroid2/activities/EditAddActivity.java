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
import com.example.projectjavaandroid2.helper.Data;
import com.example.projectjavaandroid2.helper.Response;
import com.example.projectjavaandroid2.helper.Utils;
import com.example.projectjavaandroid2.model.Avaliacao;
import com.example.projectjavaandroid2.model.partial.UnidadeCurricularPartial;
import com.example.projectjavaandroid2.network.GenericGetAsyncTask;
import com.example.projectjavaandroid2.network.GenericPutAsyncTask;
import com.example.projectjavaandroid2.network.HttpStatusCode;
import com.example.projectjavaandroid2.xml.XmlHandler;

import java.util.ArrayList;

public class EditAddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Avaliacao avaliacao;
    ArrayList<String> tipos;
    String tipoEscolhido;

    private Spinner tipoSpinner;
    private EditText etObs;
    private Button btOp, btCancel;
    private ProgressBar pb;
    private DatePicker dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editadd);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        avaliacao = null;
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.INVISIBLE);

        tipoSpinner = (Spinner) findViewById(R.id.tipoSpinner);
        tipos = new ArrayList<>();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tiposArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSpinner.setAdapter(adapter);
        tipoSpinner.setOnItemSelectedListener(this);

        dp = (DatePicker) findViewById(R.id.dp);
        etObs = (EditText) findViewById(R.id.etObs);

        btOp = (Button)findViewById(R.id.btOp);
        btOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                chooseMode();
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

        configureUi();
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

    public void editAvaliacaoInsideWS(Avaliacao avaliacaoToWs) {

        AvaliacaoDTO avaliacaoDTO = Converter.convertAvaliacaoEdit(avaliacaoToWs);
        final String body = XmlHandler.serializeAvaliacaoDTO2XML(avaliacaoDTO);
        GenericPutAsyncTask task = new GenericPutAsyncTask(pb, this, body) {
            protected void onPostExecuteProcessDataUI(Object object) {
                Toast.makeText(EditAddActivity.this, "Edição bem sucedida.", Toast.LENGTH_LONG).show();
            }
        };
        String address = Utils.getWSAddress(this);
        task.execute(address + "/editavaliacao/"
                + getIntent().getLongExtra("numAluno", 0)
                + '/' + getIntent().getLongExtra("senha", 1)
                + '/' + getIntent().getIntExtra("keyAvaliacao", 2));
    }

    public void addAvaliacaoInsideWs(Avaliacao avaliacaoToWs) {

        AvaliacaoDTO avaliacaoDTO = Converter.convertAvaliacaoAdd(avaliacaoToWs);
        final String body = XmlHandler.serializeAvaliacaoDTO2XML(avaliacaoDTO);
        GenericPutAsyncTask task = new GenericPutAsyncTask(pb, this, body) {
            protected void onPostExecuteProcessDataUI(Object object) {
                Toast.makeText(EditAddActivity.this, "Avaliação adicionada.", Toast.LENGTH_LONG).show();
            }
        };
        String address = Utils.getWSAddress(this);
        task.execute(address + "/addavaliacao/"
                + getIntent().getLongExtra("numAluno", 0)
                + '/' + getIntent().getLongExtra("senha", 1)
                + '/' + getIntent().getIntExtra("keyUc", 2));
    }

    public void setUi(Avaliacao avaliacao) {
        etObs.setText(avaliacao.getObservacao());
    }

    public void configureUi() {
        switch (getIntent().getIntExtra("action", 0)) {
            case Utils.ACTIVITY_MODE_ADDING :
                btOp.setText("Adicionar");
                break;
            case Utils.ACTIVITY_MODE_EDITING:
                btOp.setText("Editar");
        }
    }

    public void chooseMode() {
        switch (getIntent().getIntExtra("action", 0)) {
            case Utils.ACTIVITY_MODE_EDITING:
                Data data = new Data(dp.getDayOfMonth(), dp.getMonth(), dp.getYear());
                Avaliacao avaliacaoToWs = new Avaliacao(tipoEscolhido,
                        data.getData(),
                        etObs.getText().toString(), avaliacao.getUnidadeCurricular(),
                        getIntent().getLongExtra("numAluno", 0));
                editAvaliacaoInsideWS(avaliacaoToWs);
            case Utils.ACTIVITY_MODE_ADDING:
                Data data1 = new Data(dp.getDayOfMonth(), dp.getMonth(), dp.getYear());
                Avaliacao avaliacaoToWs1 = new Avaliacao(tipoEscolhido, data1.getData(), etObs.getText().toString(), getIntent().getLongExtra("numAluno", 0));
                addAvaliacaoInsideWs(avaliacaoToWs1);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tipoEscolhido = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), tipoEscolhido, 2000).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

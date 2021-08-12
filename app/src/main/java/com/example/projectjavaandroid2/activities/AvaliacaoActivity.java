package com.example.projectjavaandroid2.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectjavaandroid2.R;
import com.example.projectjavaandroid2.dto.AvaliacaoDTO;
import com.example.projectjavaandroid2.dto.Converter;
import com.example.projectjavaandroid2.helper.Response;
import com.example.projectjavaandroid2.helper.Utils;
import com.example.projectjavaandroid2.model.Avaliacao;
import com.example.projectjavaandroid2.network.GenericGetAsyncTask;
import com.example.projectjavaandroid2.network.HttpStatusCode;
import com.example.projectjavaandroid2.xml.XmlHandler;

public class AvaliacaoActivity extends AppCompatActivity {
    TextView etTipo, etData, etUc, etObs;
    ProgressBar pb;
    Avaliacao avaliacao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.INVISIBLE);

        etTipo = (TextView) findViewById(R.id.etTipo);
        etData = findViewById(R.id.etData);
        etUc = findViewById(R.id.etUc);
        etObs = findViewById(R.id.etObs);

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
        etTipo.setText(avaliacao.getTipo());
        etData.setText(avaliacao.getDataAvaliacao());
        etObs.setText(avaliacao.getObservacao());
        etUc.setText(avaliacao.getUnidadeCurricular());
    }
}

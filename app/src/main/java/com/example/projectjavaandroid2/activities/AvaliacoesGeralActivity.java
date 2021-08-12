package com.example.projectjavaandroid2.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectjavaandroid2.R;
import com.example.projectjavaandroid2.adapter.ListViewAdapterAvaliacaoGeral;
import com.example.projectjavaandroid2.dto.Converter;
import com.example.projectjavaandroid2.dto.ListaAvaliacaoPartialDTO;
import com.example.projectjavaandroid2.helper.Response;
import com.example.projectjavaandroid2.helper.Utils;
import com.example.projectjavaandroid2.model.Avaliacao;
import com.example.projectjavaandroid2.model.partial.Avaliacao1Partial;
import com.example.projectjavaandroid2.model.partial.AvaliacaoPartial;
import com.example.projectjavaandroid2.model.partial.ListaAvaliacaoPartial;
import com.example.projectjavaandroid2.network.GenericGetAsyncTask;
import com.example.projectjavaandroid2.network.HttpStatusCode;
import com.example.projectjavaandroid2.xml.XmlHandler;

import java.util.ArrayList;

public class AvaliacoesGeralActivity extends AppCompatActivity {

    ListView lv;
    ProgressBar pb;
    ArrayList<AvaliacaoPartial> avaliacaoPartials;
    ListViewAdapterAvaliacaoGeral adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacoes_geral);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.INVISIBLE);

        lv = (ListView) findViewById(R.id.listView);
        avaliacaoPartials = new ArrayList<>();
        adapter = new ListViewAdapterAvaliacaoGeral(this, R.layout.listview_avaliacao_item, avaliacaoPartials);
        lv.setAdapter(adapter);


        //Quando clica numa unidade curricular.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AvaliacaoPartial avaliacaoPartial = (AvaliacaoPartial) adapter.getItem(i);

                //Cria o intent e começa a nova activity.
                Intent intent = new Intent(AvaliacoesGeralActivity.this, AvaliacaoActivity.class);
                intent.putExtra("numAluno", getIntent().getLongExtra("numAluno", 0));
                intent.putExtra("senha", getIntent().getLongExtra("senha", 1));
                intent.putExtra("keyAvaliacao", avaliacaoPartial.getKeyAvaliacao());
                startActivity(intent);

            }
        });

        getAvaliacoesFromWs();
    }

    private void getAvaliacoesFromWs() {
        //Cria um thread secundária.
        GenericGetAsyncTask task = new GenericGetAsyncTask(pb, this) {
            protected Response getResponseObject(String httpResponse) {
                ListaAvaliacaoPartialDTO listaAvaliacaoPartialDTO = XmlHandler.deSerializeXML2ListaAvaliacaoPartialDTO(httpResponse);
                ListaAvaliacaoPartial listaAvaliacaoPartial = Converter.convertListaAvaliacaoPartialDTO(listaAvaliacaoPartialDTO);
                return new Response(HttpStatusCode.OK, listaAvaliacaoPartial);
            }

            protected void onPostExecuteProcessDataUI(Object object) {
                if (object instanceof ListaAvaliacaoPartial) {
                    avaliacaoPartials.clear();
                    avaliacaoPartials.addAll(((ListaAvaliacaoPartial) object).getAvaliacoes());
                } else {
                    avaliacaoPartials.clear();
                }
                adapter.notifyDataSetChanged();
            }
        };
        //Obtêm o endereço.
        String address = Utils.getWSAddress(this);
        task.execute(address + "/getlistaavaliacaopartial/" + getIntent().getLongExtra("numAluno", 0)
                + "/" + getIntent().getLongExtra("senha", 1));
    }
}

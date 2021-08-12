package com.example.projectjavaandroid2.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectjavaandroid2.R;
import com.example.projectjavaandroid2.adapter.ListViewAdapterUc;
import com.example.projectjavaandroid2.dto.Converter;
import com.example.projectjavaandroid2.dto.ListaUcPartialDTO;
import com.example.projectjavaandroid2.helper.Response;
import com.example.projectjavaandroid2.helper.Utils;
import com.example.projectjavaandroid2.model.partial.ListaUcPartial;
import com.example.projectjavaandroid2.model.partial.UnidadeCurricularPartial;
import com.example.projectjavaandroid2.network.GenericGetAsyncTask;
import com.example.projectjavaandroid2.network.HttpStatusCode;
import com.example.projectjavaandroid2.xml.XmlHandler;

import java.util.ArrayList;

public class UcsActivity extends AppCompatActivity {

    ListView lv;
    ProgressBar pb;
    ArrayList<UnidadeCurricularPartial> ucPartials;
    ListViewAdapterUc adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucs);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.INVISIBLE);

        lv = (ListView) findViewById(R.id.listView);
        ucPartials = new ArrayList<>();
        adapter = new ListViewAdapterUc(this, R.layout.listview_uc_item, ucPartials);
        lv.setAdapter(adapter);


        //Quando clica numa unidade curricular.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UnidadeCurricularPartial ucPartial = (UnidadeCurricularPartial) adapter.getItem(i);
                //Cria o intent e começa a nova activity.
                Intent intent = new Intent(UcsActivity.this, AvaliacoesUcActivity.class);
                intent.putExtra("numAluno",getIntent().getLongExtra("numAluno", 0));
                intent.putExtra("senha", getIntent().getLongExtra("senha", 1));
                intent.putExtra("keyUc", ucPartial.getKeyUnidadeCurricular());
                startActivity(intent);
            }
        });

        getUcsFromWs();
    }

    private void getUcsFromWs() {
        //Cria um thread secundária.
        GenericGetAsyncTask task = new GenericGetAsyncTask(pb, this) {
            protected Response getResponseObject(String httpResponse) {
                ListaUcPartialDTO listaUcPartialDTO = XmlHandler.deSerializeXML2ListaUcPartialDTO(httpResponse);
                ListaUcPartial listaUcPartial = Converter.convertListaUcPartialDTO(listaUcPartialDTO);
                return new Response(HttpStatusCode.OK, listaUcPartial);
            }

            protected void onPostExecuteProcessDataUI(Object object) {
                if (object instanceof ListaUcPartial) {
                    ucPartials.clear();
                    ucPartials.addAll(((ListaUcPartial) object).getUcs());
                } else {
                    ucPartials.clear();
                }
                adapter.notifyDataSetChanged();
            }
        };
        //Obtêm o endereço.
        String address = Utils.getWSAddress(this);
        task.execute(address + "/getlistaucpartial/" + getIntent().getLongExtra("numAluno", 0)
                + "/" + getIntent().getLongExtra("senha", 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        Toast.makeText(UcsActivity.this,"Option menu: Listar",Toast.LENGTH_SHORT).show();
        intent = new Intent(UcsActivity.this, AvaliacoesGeralActivity.class);
        intent.putExtra("numAluno", getIntent().getLongExtra("numAluno", 0));
        intent.putExtra("senha", getIntent().getLongExtra("senha", 0));
        startActivity(intent);

        return true;
    }
}

package com.example.projectjavaandroid2.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectjavaandroid2.R;
import com.example.projectjavaandroid2.adapter.ListViewAdapterAvaliacaoUc;
import com.example.projectjavaandroid2.dto.Converter;
import com.example.projectjavaandroid2.dto.ListaAvaliacaoPartial1DTO;
import com.example.projectjavaandroid2.helper.Response;
import com.example.projectjavaandroid2.helper.Utils;
import com.example.projectjavaandroid2.model.Avaliacao;
import com.example.projectjavaandroid2.model.partial.Avaliacao1Partial;
import com.example.projectjavaandroid2.model.partial.ListaAvaliacao1Partial;
import com.example.projectjavaandroid2.network.GenericGetAsyncTask;
import com.example.projectjavaandroid2.network.HttpStatusCode;
import com.example.projectjavaandroid2.xml.XmlHandler;

import java.util.ArrayList;

public class AvaliacoesUcActivity extends AppCompatActivity {

    ListView lv;
    ProgressBar pb;
    ArrayList<Avaliacao1Partial> avaliacao1Partials;
    ListViewAdapterAvaliacaoUc adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacoes_uc);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.INVISIBLE);

        lv = (ListView) findViewById(R.id.listView);
        avaliacao1Partials = new ArrayList<>();
        adapter = new ListViewAdapterAvaliacaoUc(this, R.layout.listview_avaliacaou1_item, avaliacao1Partials);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);


        //Quando clica numa unidade curricular.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Avaliacao1Partial avaliacao1Partial = (Avaliacao1Partial) adapter.getItem(i);
                //Cria o intent e começa a nova activity.
                Intent intent = new Intent(AvaliacoesUcActivity.this, AvaliacaoActivity.class);
                intent.putExtra("numAluno", getIntent().getLongExtra("numAluno", 0));
                intent.putExtra("senha", getIntent().getLongExtra("senha", 0));
                intent.putExtra("keyAvaliacao", avaliacao1Partial.getKeyAvaliacao());
                startActivity(intent);
            }
        });

        getAvaliacoes1FromWs();
    }

    private void getAvaliacoes1FromWs() {
        //Cria um thread secundária.
        GenericGetAsyncTask task = new GenericGetAsyncTask(pb, this) {
            protected Response getResponseObject(String httpResponse) {
                ListaAvaliacaoPartial1DTO listaAvaliacaoPartial1DTO = XmlHandler.deSerializeXML2ListaAvaliacaoPartial1DTO(httpResponse);
                ListaAvaliacao1Partial listaAvaliacaoPartial = Converter.convertListaAvaliacaoPartial1DTO(listaAvaliacaoPartial1DTO);
                return new Response(HttpStatusCode.OK, listaAvaliacaoPartial);
            }

            protected void onPostExecuteProcessDataUI(Object object) {
                if (object instanceof ListaAvaliacao1Partial) {
                    avaliacao1Partials.clear();
                    avaliacao1Partials.addAll(((ListaAvaliacao1Partial) object).getAvaliacoes());
                } else {
                    avaliacao1Partials.clear();
                }
                adapter.notifyDataSetChanged();
            }
        };

        //Obtêm o endereço.
        String address = Utils.getWSAddress(this);
        task.execute(address + "/getlistaavaliacao1partial/"
                + getIntent().getLongExtra("numAluno", 0)
                + "/" + getIntent().getLongExtra("senha", 1)
                + '/' + getIntent().getIntExtra("keyUc", 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        Toast.makeText(AvaliacoesUcActivity.this,"Option menu: Listar",Toast.LENGTH_SHORT).show();
        intent = new Intent(AvaliacoesUcActivity.this, EditAddActivity.class);
        intent.putExtra("numAluno", getIntent().getLongExtra("numAluno", 0));
        intent.putExtra("senha", getIntent().getLongExtra("senha", 0));
        intent.putExtra("keyUc", getIntent().getIntExtra("keyUc", 0));
        intent.putExtra("action", Utils.ACTIVITY_MODE_ADDING);
        startActivityForResult(intent, Utils.REQUEST_CODE_ADD_ACTIVITY);

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        Intent intent;
        //Obtêm a avaliação parcial 1 correspondente ao item selecionado na list view.
        Avaliacao1Partial avaliacao1Partial = (Avaliacao1Partial) adapter.getItem(pos);
        if (avaliacao1Partial != null) {
            //Obtêm o item selecionado dentro do context menu.
            switch (item.getItemId()) {
                case R.id.delete:
                    Toast.makeText(AvaliacoesUcActivity.this,"Eliminar",Toast.LENGTH_SHORT).show();
                    intent = new Intent(AvaliacoesUcActivity.this, DeleteActivity.class);
                    intent.putExtra("numAluno", getIntent().getLongExtra("numAluno", 0));
                    intent.putExtra("senha", getIntent().getLongExtra("senha", 1));
                    intent.putExtra("keyAvaliacao", avaliacao1Partial.getKeyAvaliacao());
                    startActivityForResult(intent, Utils.REQUEST_CODE_DELETE_ACTIVITY);
                    return true;
                case R.id.edit:
                    Toast.makeText(AvaliacoesUcActivity.this,"Editar",Toast.LENGTH_SHORT).show();
                    intent = new Intent(AvaliacoesUcActivity.this, EditAddActivity.class);
                    intent.putExtra("numAluno", getIntent().getLongExtra("numAluno", 0));
                    intent.putExtra("senha", getIntent().getLongExtra("senha", 1));
                    intent.putExtra("keyAvaliacao", avaliacao1Partial.getKeyAvaliacao());
                    intent.putExtra("action", Utils.ACTIVITY_MODE_EDITING);
                    startActivityForResult(intent, Utils.REQUEST_CODE_EDIT_ACTIVITY);
                    return true;
                default:
                    break;
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if (resultCode == Activity.RESULT_OK ||
                 resultCode == Activity.RESULT_CANCELED) {
             getAvaliacoes1FromWs();
         }
    }
}


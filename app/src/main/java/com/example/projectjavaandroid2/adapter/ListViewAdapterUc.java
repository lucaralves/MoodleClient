package com.example.projectjavaandroid2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.projectjavaandroid2.R;
import com.example.projectjavaandroid2.model.partial.UnidadeCurricularPartial;

import java.util.ArrayList;

public class ListViewAdapterUc extends BaseAdapter {

    private Context context;
    private int layoutId;
    private final ArrayList<UnidadeCurricularPartial> items;

    public ListViewAdapterUc(Context context, int layoutId, ArrayList<UnidadeCurricularPartial> items) {
        this.context = context;
        this.layoutId = layoutId;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //O número de vezes que o método é repetido
    //é definido a partir do tamanho do ArrayList enviado por prâmetro.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Obtêm a pessoa que vai constituir uma das views.
        final UnidadeCurricularPartial row = this.items.get(position);
        View itemView = null;

        //Condição devido ao deslizar do ecrã.
        if (convertView == null) {
            //Cria o inflater.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Torna a itemview num objeto programável.
            itemView = inflater.inflate(layoutId, null);
        } else {
            //Caso não esteja nula a itemview recebe o seu valor.
            itemView = convertView;
        }

        //Obtêm a text view do nif.
        TextView nif = (TextView) itemView.findViewById(R.id.textViewCodigo);
        nif.setText(row.getKeyUnidadeCurricular()+"");
        //Obtêm a text view do nome.
        TextView nome = (TextView) itemView.findViewById(R.id.textViewNome);
        nome.setText(row.getNome());

        return itemView;
    }
}

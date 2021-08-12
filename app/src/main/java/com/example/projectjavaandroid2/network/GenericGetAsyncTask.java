package com.example.projectjavaandroid2.network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.projectjavaandroid2.dto.ErroDTO;
import com.example.projectjavaandroid2.helper.Response;
import com.example.projectjavaandroid2.helper.Utils;
import com.example.projectjavaandroid2.xml.XmlHandler;

public class GenericGetAsyncTask extends AsyncTask<String, Void, Response> {

    private ProgressBar pb;
    private Context context;

    public GenericGetAsyncTask(ProgressBar pb, Context context) {
        this.pb = pb;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pb.setVisibility(ProgressBar.VISIBLE);
    }

    protected Response doInBackground(String... params) {
        Response response = null;

        //Cria o htt request do tipo get.
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, params[0], "");
        //Faz o pedido.
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        //Obtêm o código de resposta.
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                //Obtêm o body.
                response = getResponseObject(httpResponse.getBody());
                break;
            case HttpStatusCode.Conflict:
                //Cria um ErroDTO.
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    @Override
    protected void onPostExecute(Response result) {
        super.onPostExecute(result);
        if (result != null) {
            //Obtêm o objeto apenas que está no body da resposta sem o código.
            Object object = result.getBody();
            switch (result.getStatus()) {
                case HttpStatusCode.OK:
                    onPostExecuteProcessDataUI(object);
                    break;
                case HttpStatusCode.Conflict:
                    if (object instanceof String) {
                        String message = (String) object;
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    Toast.makeText(context, Utils.UNKNOWN_ACTION, Toast.LENGTH_LONG).show();
                    break;
            }
        }
        pb.setVisibility(ProgressBar.GONE);
        context = null;
    }

    protected void onPostExecuteProcessDataUI(Object object) {

    }

    protected Response getResponseObject(String httpResponse) {
        throw new UnsupportedOperationException("A operação getResponseObject tem de ser reescrita.");
    }
}

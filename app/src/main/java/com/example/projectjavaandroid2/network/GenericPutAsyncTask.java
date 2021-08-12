package com.example.projectjavaandroid2.network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.projectjavaandroid2.dto.ErroDTO;
import com.example.projectjavaandroid2.helper.Response;
import com.example.projectjavaandroid2.helper.Utils;
import com.example.projectjavaandroid2.xml.XmlHandler;

public class GenericPutAsyncTask extends AsyncTask<String, Void, Response> {

    private ProgressBar pb;
    private Context context;
    private String body;

    public GenericPutAsyncTask(ProgressBar pb, Context context, String body) {
        this.pb = pb;
        this.context = context;
        this.body = body;
    }

    @Override
    protected void onPreExecute() {
        pb.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected Response doInBackground(String... params) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.PUT, params[0], body);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                response = new Response(HttpStatusCode.OK, null);
                break;
            case HttpStatusCode.Conflict:
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
            Object object = result.getBody();
            switch (result.getStatus()) {
                case HttpStatusCode.OK:
                    onPostExecuteProcessResponseObject(object);
                    break;
                case HttpStatusCode.Conflict:
                    if (object instanceof String) {
                        String message = (String) object;
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    }
                default:
                    Toast.makeText(context, Utils.UNKNOWN_ACTION, Toast.LENGTH_LONG).show();
                    break;
            }
        }
        pb.setVisibility(ProgressBar.GONE);
    }

    public void onPostExecuteProcessResponseObject(Object httpResponse) {

    }

}

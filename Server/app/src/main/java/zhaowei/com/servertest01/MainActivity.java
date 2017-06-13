package zhaowei.com.servertest01;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button btn_post = null;
    private TextView tv_rp = null;
    TextView textViewURL = null;
    TextView textViewPara = null;

    private EditText etUrl;
    private EditText etPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_post = (Button) this.findViewById(R.id.myBtn);
        tv_rp = (TextView) this.findViewById(R.id.myTv);
        textViewURL = (TextView)findViewById(R.id.textViewURL);
        textViewPara = (TextView)findViewById(R.id.textViewPara);

        etUrl = (EditText) findViewById(R.id.edit_eg_url);
        etPort = (EditText) findViewById(R.id.edit_eg_port);

        btn_post.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                queryVolley();
            }
        });

    }

    private void queryVolley(){

        LoadingDialog.showDialog( MainActivity.this );
        JSONObject params = new JSONObject();
        try {
            params.put("CarId",1);
            params.put("UserName","user1");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String strUrl = "http://"+ etUrl.getText() +":" +etPort.getText() + "/transportservice/action/GetCarAccountBalance.do";
//        String strUrl = "http://"+ etUrl.getText() +":" +etPort.getText() + "/transportservice/action/GetAllCarPeccancy.do";

        textViewURL.setText("");
        textViewURL.setText(strUrl);

        textViewPara.setText("");
        textViewPara.setText(params.toString());

        RequestQueue mQueue = Volley.newRequestQueue( MainActivity.this );
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, strUrl, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stu
                Log.d("TAG volley", response.toString());
                String str = response.toString();

                LoadingDialog.disDialog();
                if ( response.optString("RESULT").equals("S")){
                    Toast.makeText(getApplicationContext(), response.optString("ERRMSG"),Toast.LENGTH_SHORT).show();
                }
                resultSettext(str);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                LoadingDialog.disDialog();
                Toast.makeText(getApplicationContext(), "失败",Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(jsonObjectRequest);
    }

    private void resultSettext(String stContent) {
        tv_rp.setText( "" );
        tv_rp.setText( stContent );
    }

}

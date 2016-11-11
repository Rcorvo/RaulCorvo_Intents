package net.example.intents;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    private Button btn_web, btn_call, btn_maps, btn_camera, btn_send_mail, btn_web_search, btn_dialer, btn_street_view, btn_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_web = (Button) findViewById(R.id.btn_web);
        btn_call = (Button) findViewById(R.id.btn_call);
        btn_maps = (Button) findViewById(R.id.btn_maps);
        btn_camera = (Button) findViewById(R.id.btn_camera);
        btn_send_mail = (Button) findViewById(R.id.btn_send_mail);
        btn_web_search = (Button) findViewById(R.id.btn_web_search);
        btn_dialer = (Button) findViewById(R.id.btn_dialer);
        btn_street_view = (Button) findViewById(R.id.btn_street_view);
        btn_share = (Button) findViewById(R.id.btn_share);

        btn_web.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_maps.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
        btn_send_mail.setOnClickListener(this);
        btn_web_search.setOnClickListener(this);
        btn_dialer.setOnClickListener(this);
        btn_street_view.setOnClickListener(this);
        btn_share.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_web:
                pgWeb();
                break;
            case R.id.btn_call:
                llamadaTelefono();
                break;
            case R.id.btn_maps:
                googleMaps();
                break;
            case R.id.btn_camera:
                hacerFoto();
                break;
            case R.id.btn_send_mail:
                mandarCorreo();
                break;
            case R.id.btn_web_search:
                webSearch();
                break;
            case R.id.btn_dialer:
                dialerPhone();
                break;
            case R.id.btn_street_view:
                streetView();
                break;
            case R.id.btn_share:
                share();
                break;
        }
    }

    private void pgWeb(){
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://campus.somtic.net/"));
        startActivity(intent);
    }

    private void llamadaTelefono(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(this.checkSelfPermission(Manifest.permission.CALL_PHONE) ==
                    PackageManager.PERMISSION_GRANTED){
                intent = new Intent(Intent.ACTION_CALL, Uri.parse(
                        "tel:966870700"));
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Permisos no concedidos!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:966870700"));
            startActivity(intent);
        }
    }

    private void googleMaps(){
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:38.553468,-0.121579"));
        startActivity(intent);
    }

    private void hacerFoto(){
        intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

    private void mandarCorreo(){
        intent = new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, "asunto")
                .putExtra(Intent.EXTRA_TEXT, "texto del correo")
                .putExtra(Intent.EXTRA_EMAIL, new String[]
                        {"smira@iesperemaria.com"});
        startActivity(intent);
    }

    private void webSearch(){
        intent = new Intent(Intent.ACTION_WEB_SEARCH)
                .putExtra(SearchManager.QUERY, "IES Pere Maria Orts");
        startActivity(intent);
    }

    private void dialerPhone(){
        intent = new Intent(Intent.ACTION_DIAL, Uri.parse(
                "tel:966870700"));
        startActivity(intent);
    }

    private void streetView(){
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "google.streetview:cbll=38.553468,-0.121579"));
        startActivity(intent);
    }

    private void share(){
        intent = new Intent(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, "Compartido desde IES Pere Maria Orts")
                .setType("text/plain");
        String titulo = getResources().getString(R.string.btn_share);
        Intent chooser = Intent.createChooser(intent, titulo);
        if(chooser.resolveActivity(getPackageManager()) != null){
            startActivity(chooser);
        }
    }
}

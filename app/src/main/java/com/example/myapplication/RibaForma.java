package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RibaForma extends AppCompatActivity {
    private static String textDuzinaForma;
    private static String textMamacForma;
    private static String textRibaForma;
    private static String textTezinaForma;
    private static String textDatumForma;
    private static String textPZForma;
    TextView textRiba;
    TextView textDuzina;
    TextView textMamac ;
    TextView textTezina;
    TextView textDatum ;
    TextView textPZ ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riba_forma);

        TextView textRiba =      findViewById(R.id.textRiba);
        TextView textDuzina =     findViewById(R.id.textDuzina);
        TextView textMamac =     findViewById(R.id.textMamac);
        TextView textTezina =     findViewById(R.id.textTezina);
        TextView textDatum =     findViewById(R.id.textDatum);
        TextView textPZ =     findViewById(R.id.textPZ);


        ColorDrawable lijevo = new ColorDrawable(Color.BLACK);
        ColorDrawable desno = new ColorDrawable(Color.BLACK);
        ColorDrawable dolje = new ColorDrawable(Color.WHITE);
        ColorDrawable gore = new ColorDrawable(Color.BLACK);
        ColorDrawable p = new ColorDrawable(Color.BLACK);
        Drawable [] layers = new Drawable[]{
            lijevo,gore,desno,dolje,p
        };

        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0 ,0,0,15,0);
        layerDrawable.setLayerInset(1 ,15,0,0,15);
        layerDrawable.setLayerInset(2 ,15,15,0,0);
        layerDrawable.setLayerInset(3 ,15,15,15,0);
        layerDrawable.setLayerInset(4 ,15,15,15,15);

        textRiba.setBackground(layerDrawable);
        textRiba.setTextColor(Color.WHITE);

        textDuzina.setBackground(layerDrawable);
        textDuzina.setTextColor(Color.WHITE);

        textMamac.setBackground(layerDrawable);
        textMamac.setTextColor(Color.WHITE);

       textTezina.setBackground(layerDrawable);
       textTezina.setTextColor(Color.WHITE);

        textDatum.setBackground(layerDrawable);
        textDatum.setTextColor(Color.WHITE);

        textPZ.setBackground(layerDrawable);
        textPZ.setTextColor(Color.WHITE);


    }

    public void podatciSalji(View view) {


        TextView textRiba =      findViewById(R.id.textRiba);
        TextView textDuzina =     findViewById(R.id.textDuzina);
        TextView textMamac =     findViewById(R.id.textMamac);
        TextView textTezina =     findViewById(R.id.textTezina);
        TextView textDatum =     findViewById(R.id.textDatum);
        TextView textPZ =     findViewById(R.id.textPZ);


        textDuzinaForma = textDuzina.getText().toString();
        textMamacForma = textMamac.getText().toString();
        textRibaForma = textRiba.getText().toString();
        textTezinaForma= textTezina.getText().toString();
        textDatumForma= textDatum.getText().toString();
        textPZForma= textPZ.getText().toString();

        openActivityMapsActivity();

}

    public void openActivityMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        MapsActivity.setForm(textDuzinaForma, textMamacForma, textRibaForma, textTezinaForma, textDatumForma, textPZForma);
    }

    public void nazad(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }



}
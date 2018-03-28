package brandon.example.com.youtubeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private YouTubePlayerView youTubePlayerView;
    private String key = "AIzaSyAXG6EwxF8oCSTvuSm35mcYGV4kYAF--fU";
    String uri ="8onB7rPG4Pk";
    private EditText editText;
    private Button buscar, historial;
    private ArrayList<String> guardados = new ArrayList<>();
    private YouTubePlayer player;

    private ListView listView;
    private DatoAdapter datoAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.you);
        youTubePlayerView.initialize(key,this);
        editText = (EditText) findViewById(R.id.editText);
        buscar = (Button) findViewById(R.id.button);
        historial = (Button) findViewById(R.id.button2);
        listView = (ListView) findViewById(R.id.lista);

        datoAdapter = new DatoAdapter(this,R.layout.dato_layout, new ArrayList<Dato>());



        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uri = editText.getText().toString();
                guardados.add(uri);
                player.cueVideo(uri);
                Dato dato = new Dato();
                dato.uri = uri;
                datoAdapter.add(dato);
                datoAdapter.notifyDataSetChanged();
            }
        });

        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listView.setAdapter(datoAdapter);

            }
        });
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Toast.makeText(this, "Youtube Enabled", Toast.LENGTH_SHORT).show();
        this.player = youTubePlayer;
        if(!b){
            youTubePlayer.cueVideo(uri);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Youtube Error Not Available", Toast.LENGTH_SHORT).show();
    }
}

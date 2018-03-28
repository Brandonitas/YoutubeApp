package brandon.example.com.youtubeapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by brandon on 27/03/18.
 */

public class DatoAdapter extends ArrayAdapter<Dato> {
    Context context;

    public DatoAdapter(@NonNull Context context, int resource, @NonNull List<Dato> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Dato midato = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.dato_layout,parent,false);
        }

        TextView textView = convertView.findViewById(R.id.dato);
        textView.setText(midato.uri);

        return convertView;
    }
}

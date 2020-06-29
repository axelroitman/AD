package com.example.loginclinicapp;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import retrofit2.Callback;

public class GroupAdp extends RecyclerView.Adapter<GroupAdp.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Turno> turnos;


    //constructor
    GroupAdp(Context context, List<Turno> turnos){
        this.layoutInflater = layoutInflater.from(context);
        this.turnos = turnos;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDiaTurno1, txtMesTurno1, txtDiaSemanaTurno1, txtHorario1, txtMedico1, txtEspecialidad1, txtestado4, txtasistencia4;
        ImageView imgestado4, imgasistencia4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDiaTurno1 = itemView.findViewById(R.id.txtDiaTurno1);
            txtMesTurno1 = itemView.findViewById(R.id.txtMesTurno1);
            txtDiaSemanaTurno1 = itemView.findViewById(R.id.txtDiaSemanaTurno1);
            txtHorario1 = itemView.findViewById(R.id.txtHorario1);
            txtMedico1 = itemView.findViewById(R.id.txtMedico1);
            txtEspecialidad1 = itemView.findViewById(R.id.txtEspecialidad1);
            txtestado4 = itemView.findViewById(R.id.txtestado4);
            txtasistencia4 = itemView.findViewById(R.id.txtasistencia4);
            imgestado4 = itemView.findViewById(R.id.imgestado4);
            imgasistencia4 = itemView.findViewById(R.id.imgasistencia4);
        }
    }

    @NonNull
    @Override
    public GroupAdp.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.itemturno, viewGroup,false);
        return new GroupAdp.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fecha = LocalDateTime.parse(turnos.get(i).getFecha(), formato);
        String diaEnPalabras = fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es","ES"));
        String mesEnPalabras = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"));


        String horario = fecha.getHour() + ":" + (fecha.getMinute() < 10 ? "0" : "") + fecha.getMinute() + "hs.";
        viewHolder.txtHorario1.setText(horario);

        String dia = ""+fecha.getDayOfMonth();
        viewHolder.txtDiaTurno1.setText(dia);

        viewHolder.txtMesTurno1.setText(mesEnPalabras.toUpperCase().substring(0,3) + ".");

        viewHolder.txtDiaSemanaTurno1.setText(diaEnPalabras.toUpperCase().substring(0,1) + diaEnPalabras.substring(1));



        String medico = turnos.get(i).getMedico().getNombre();
        viewHolder.txtMedico1.setText(medico);

        String especialidad = turnos.get(i).getEspecialidad().getNombre();
        viewHolder.txtEspecialidad1.setText(especialidad);

        /*String estado = data.get(i);
        viewHolder.txtestado4.setText(estado);

        String asistencia = data.get(i);
        viewHolder.txtasistencia4.setText(asistencia);*/

        //me faltarian poner las imagenes.

    }

    @Override
    public int getItemCount() {
        return turnos.size();
    }
}

package com.example.loginclinicapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Layout;
import android.util.Log;
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
    int idUsr;
    int idPaciente;
    String matricula;
    String nombre;

    //constructor
    GroupAdp(Context context, List<Turno> turnos, int idUsr, int idPaciente, String matricula, String nombre){
        this.layoutInflater = layoutInflater.from(context);
        this.turnos = turnos;
        this.idUsr = idUsr;
        this.idPaciente = idPaciente;
        this.matricula = matricula;
        this.nombre = nombre;
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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final int idTurno = turnos.get(i).getId();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(viewHolder.itemView.getContext(), DetalleTurno.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                i.putExtra("idTurno", idTurno);

                viewHolder.itemView.getContext().startActivity(i);
            }
        });


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

        if(turnos.get(i).getMedico().getMatricula().equals(matricula))
        {
            if(turnos.get(i).getPaciente() != null) {
                String paciente = turnos.get(i).getPaciente().getNombre();
                viewHolder.txtMedico1.setText(paciente);
            }
            else{
                viewHolder.txtMedico1.setText("Sin paciente");
            }
        }
        else {
            String medico = turnos.get(i).getMedico().getNombre();
            viewHolder.txtMedico1.setText(medico);
        }
        String especialidad = turnos.get(i).getEspecialidad().getNombre();
        viewHolder.txtEspecialidad1.setText(especialidad);

        if(turnos.get(i).getAsistencia().equals("NoAsiste")) {
            viewHolder.imgasistencia4.setImageResource(R.drawable.asistenciano);
        }
        if(turnos.get(i).getAsistencia().equals("Asiste")) {
            viewHolder.imgasistencia4.setImageResource(R.drawable.asistenciaok);
        }
        else {
            viewHolder.imgasistencia4.setImageResource(R.drawable.asistenciapendiente);
        }

        if(turnos.get(i).getDisponibilidad().equals("Disponible")) {
            viewHolder.imgestado4.setImageResource(R.drawable.ok);
        }
        if(turnos.get(i).getDisponibilidad().equals("Programado")) {
            viewHolder.imgestado4.setImageResource(R.drawable.ok);
        }
        if(turnos.get(i).getDisponibilidad().equals("AConfirmar")) {
            viewHolder.imgestado4.setImageResource(R.drawable.ok);
        }
        if(turnos.get(i).getDisponibilidad().equals("Confirmado")) {
            viewHolder.imgestado4.setImageResource(R.drawable.ok);
        }
        if(turnos.get(i).getDisponibilidad().equals("Terminado")) {
            viewHolder.imgestado4.setImageResource(R.drawable.ok);
        }
        else{
           //Cancelado
            viewHolder.imgestado4.setImageResource(R.drawable.notok);
        }
    }

    @Override
    public int getItemCount() {
        return turnos.size();
    }
}

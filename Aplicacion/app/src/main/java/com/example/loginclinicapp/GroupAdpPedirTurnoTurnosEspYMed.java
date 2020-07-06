package com.example.loginclinicapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

class GroupAdpPedirTurnoTurnosEspYMed extends RecyclerView.Adapter<GroupAdpPedirTurnoTurnosEspYMed.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Turno> turnos;

    int idUsr;
    int idTurno;
    int idPaciente;
    String matricula;
    String nombre;
    int idEsp;
    String nombreEsp;
    String nombreMedico;
    String matriculaSeleccionado;
    LocalDateTime dateTime;
    String hora;
    String minuto;

    //constructor
    GroupAdpPedirTurnoTurnosEspYMed(Context context, List<Turno> turnos, int idUsr, int idPaciente, String matricula, String nombre, int idEsp, String matriculaSeleccionado, String nombreEsp, String nombreMedico){
        this.layoutInflater = layoutInflater.from(context);
        this.turnos = turnos;
        this.idUsr = idUsr;
        this.idPaciente = idPaciente;
        this.matricula = matricula;
        this.nombre = nombre;
        this.idEsp = idEsp;
        this.matriculaSeleccionado = matriculaSeleccionado;
        this.nombreEsp = nombreEsp;
        this.nombreMedico = nombreMedico;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {                                   //LEP

        TextView txtHorario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHorario = itemView.findViewById(R.id.txtHorario);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {                     //LEP
        View view = layoutInflater.inflate(R.layout.itemturnoturnosespymedico, viewGroup,false);
        return new GroupAdpPedirTurnoTurnosEspYMed.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                                               //LEP
                    Intent i = new Intent(viewHolder.itemView.getContext(), DetalleTurno.class);
                    idTurno = turnos.get(viewHolder.getAdapterPosition()).getId();
                    i.putExtra("idUsr", idUsr);
                    i.putExtra("idPaciente", idPaciente);
                    i.putExtra("matricula", matricula);
                    i.putExtra("nombre", nombre);
                    i.putExtra("idTurno", idTurno);
                    viewHolder.itemView.getContext().startActivity(i);
                }
            });
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime = LocalDateTime.parse(turnos.get(i).getFecha(), formatter);

            if (dateTime.getHour() < 10) {
                hora = "0" + dateTime.getHour();
            } else {
                hora = "" + dateTime.getHour();
            }
            if (dateTime.getMinute() < 10) {
                minuto = "0" + dateTime.getMinute() + "hs";
            } else {
                minuto = "" + dateTime.getMinute() + "hs";
            }
            idTurno = turnos.get(i).getId();
            viewHolder.txtHorario.setText(hora + ":" + minuto);
    }


    @Override
    public int getItemCount() {
        if(turnos!=null) {
            return turnos.size();
        }
        else{
            return 0;
        }
    }
}


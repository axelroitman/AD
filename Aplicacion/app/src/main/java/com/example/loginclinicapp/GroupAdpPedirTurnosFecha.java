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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

class GroupAdpPedirTurnosFecha extends RecyclerView.Adapter<GroupAdpPedirTurnosFecha.ViewHolder> {
    private LayoutInflater layoutInflater;
    private TreeMap<Date, Integer> turnos;
    private List<Date> fechas = new ArrayList<Date>();
    private List<Integer> cant = new ArrayList<Integer>();



    int idUsr;
    int idPaciente;
    String matricula;
    String nombre;
    int idEsp;
    String nombreEsp;
    String nombreMedico;
    String matriculaSeleccionado;
    private LocalDate fecha;

    //constructor
    GroupAdpPedirTurnosFecha(Context context, TreeMap<Date, Integer> turnos,int idUsr,int idPaciente,String matricula, String nombre,int idEsp,String matriculaSeleccionado,String nombreEsp, String nombreMedico){
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

        TextView txtDiaTurno1, txtMesTurno1, txtDiaSemanaTurno1, cantidadTurnosDisponibles;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDiaTurno1 = itemView.findViewById(R.id.txtDiaTurno1);
            txtMesTurno1 = itemView.findViewById(R.id.txtMesTurno1);
            txtDiaSemanaTurno1 = itemView.findViewById(R.id.txtDiaSemanaTurno1);
            cantidadTurnosDisponibles = itemView.findViewById(R.id.cantidadTurnosDisponibles);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {                     //LEP
        View view = layoutInflater.inflate(R.layout.itemturnofechas, viewGroup,false);
        return new GroupAdpPedirTurnosFecha.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        fechas.addAll(turnos.keySet());
        cant.addAll(turnos.values());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                fecha = LocalDate.parse(df.format(fechas.get(viewHolder.getAdapterPosition())), formato);
                Intent i = new Intent(viewHolder.itemView.getContext(), pedir_turno_turnos.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente", idPaciente);
                i.putExtra("matricula", matricula);
                i.putExtra("nombre", nombre);
                i.putExtra("idEspecialidad", idEsp);
                i.putExtra("nombreEsp", nombreEsp);
                i.putExtra("matriculaSeleccionado", matriculaSeleccionado);
                i.putExtra("nombreMedico", nombreMedico);
                i.putExtra("fecha",fecha);


                viewHolder.itemView.getContext().startActivity(i);
            }
        });
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fecha = LocalDate.parse(df.format(fechas.get(i)), formato);
        String diaEnPalabras = fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es","ES"));
        String mesEnPalabras = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"));


        String dia = ""+fecha.getDayOfMonth();
        viewHolder.txtDiaTurno1.setText(dia);

        viewHolder.txtMesTurno1.setText(mesEnPalabras.toUpperCase().substring(0,3) + ".");

        viewHolder.txtDiaSemanaTurno1.setText(diaEnPalabras.toUpperCase().substring(0,1) + diaEnPalabras.substring(1));

        if(cant.get(i) == 1){
            viewHolder.cantidadTurnosDisponibles.setText(cant.get(i) + " turno disponible");
        }
        else{
            viewHolder.cantidadTurnosDisponibles.setText(cant.get(i) + " turnos disponibles");
        }

    }

    @Override
    public int getItemCount() {
        return turnos.size();
    }
}


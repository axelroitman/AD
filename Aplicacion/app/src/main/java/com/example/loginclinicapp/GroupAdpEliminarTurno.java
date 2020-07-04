package com.example.loginclinicapp;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

class GroupAdpEliminarTurno extends RecyclerView.Adapter<GroupAdpEliminarTurno.ViewHolder>  {
    private LayoutInflater layoutInflater;
    int idUsr;
    int idPaciente;
    String matricula;
    String nombre;
    List<Turno> turnos;
    List<Turno> seleccionados;

    public GroupAdpEliminarTurno(Context context,List<Turno> turnos,int idUsr, int idPaciente, String matricula, String nombre) {
        this.layoutInflater = layoutInflater.from(context);
        this.turnos = turnos;
        this.idUsr = idUsr;
        this.idPaciente = idPaciente;
        this.matricula = matricula;
        this.nombre = nombre;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {                                   //LEP

        TextView txtDiaTurno, txtDiaSemanaTurno, txtMesTurno, tvEspecialidad, tvHorario;
        RelativeLayout infoTurnoMedico, relativeFechas;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDiaSemanaTurno = itemView.findViewById(R.id.txtDiaSemanaTurno1);
            txtDiaTurno = itemView.findViewById(R.id.txtDiaTurno1);
            txtMesTurno = itemView.findViewById(R.id.txtMesTurno1);
            tvEspecialidad = itemView.findViewById(R.id.tvEspecialidad);
            tvHorario = itemView.findViewById(R.id.tvHorario);
            infoTurnoMedico = itemView.findViewById(R.id.infoTurnoMedico);
            card = (CardView) itemView.findViewById(R.id.card);
            relativeFechas = (RelativeLayout) itemView.findViewById(R.id.relativeFechas);
        }
    }

    @NonNull
    @Override
    public GroupAdpEliminarTurno.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {                     //LEP
        View view = layoutInflater.inflate(R.layout.itemturnoeliminar, viewGroup,false);
        return new GroupAdpEliminarTurno.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final GroupAdpEliminarTurno.ViewHolder viewHolder, int i) {
        final Turno t = turnos.get(i);
        viewHolder.infoTurnoMedico.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                t.setSeleccionado(!t.isSeleccionado());
                if(t.isSeleccionado()){
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#498EB8"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#FFFFFF"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#FFFFFF"));

                    //seleccionados.add(t); //Si se toca dentro del recycler en un espacio en blanco, esta línea es la que hace que crashee
                }
                else{
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#BCFFFFFF"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#808080"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#808080"));

                    //seleccionados.remove(t);
                }
            }
        });
        viewHolder.relativeFechas.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                t.setSeleccionado(!t.isSeleccionado());
                if(t.isSeleccionado()){
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#498EB8"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#FFFFFF"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#FFFFFF"));

                    //seleccionados.add(t); //Si se toca dentro del recycler en un espacio en blanco, esta línea es la que hace que crashee
                }
                else{
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#BCFFFFFF"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#808080"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#808080"));

                    //seleccionados.remove(t);
                }
            }
        });

        viewHolder.tvEspecialidad.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                t.setSeleccionado(!t.isSeleccionado());
                if(t.isSeleccionado()){
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#498EB8"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#FFFFFF"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#FFFFFF"));

                    //seleccionados.add(t); //Si se toca dentro del recycler en un espacio en blanco, esta línea es la que hace que crashee
                }
                else{
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#BCFFFFFF"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#808080"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#808080"));

                    //seleccionados.remove(t);
                }
            }
        });

        viewHolder.txtDiaTurno.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                t.setSeleccionado(!t.isSeleccionado());
                if(t.isSeleccionado()){
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#498EB8"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#FFFFFF"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#FFFFFF"));

                    //seleccionados.add(t); //Si se toca dentro del recycler en un espacio en blanco, esta línea es la que hace que crashee
                }
                else{
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#BCFFFFFF"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#808080"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#808080"));

                    //seleccionados.remove(t);
                }
            }
        });
        viewHolder.txtMesTurno.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                t.setSeleccionado(!t.isSeleccionado());
                if(t.isSeleccionado()){
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#498EB8"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#FFFFFF"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#FFFFFF"));

                    //seleccionados.add(t); //Si se toca dentro del recycler en un espacio en blanco, esta línea es la que hace que crashee
                }
                else{
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#BCFFFFFF"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#808080"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#808080"));

                    //seleccionados.remove(t);
                }
            }
        });
        viewHolder.txtDiaSemanaTurno.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                t.setSeleccionado(!t.isSeleccionado());
                if(t.isSeleccionado()){
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#498EB8"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#FFFFFF"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#FFFFFF"));

                    //seleccionados.add(t); //Si se toca dentro del recycler en un espacio en blanco, esta línea es la que hace que crashee
                }
                else{
                    viewHolder.infoTurnoMedico.setBackgroundColor(Color.parseColor("#BCFFFFFF"));
                    viewHolder.tvHorario.setTextColor(Color.parseColor("#808080"));
                    viewHolder.tvEspecialidad.setTextColor(Color.parseColor("#808080"));

                    //seleccionados.remove(t);
                }
            }
        });
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime fecha = LocalDateTime.parse(turnos.get(i).getFecha(), formato);
                String diaEnPalabras = fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es","ES"));
                String mesEnPalabras = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"));


                String horario = fecha.getHour() + ":" + (fecha.getMinute() < 10 ? "0" : "") + fecha.getMinute() + "hs.";
                viewHolder.tvHorario.setText(horario);

                String dia = ""+fecha.getDayOfMonth();
                viewHolder.txtDiaTurno.setText(dia);

                viewHolder.txtMesTurno.setText(mesEnPalabras.toUpperCase().substring(0,3) + ".");

                viewHolder.txtDiaSemanaTurno.setText(diaEnPalabras.toUpperCase().substring(0,1) + diaEnPalabras.substring(1));

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

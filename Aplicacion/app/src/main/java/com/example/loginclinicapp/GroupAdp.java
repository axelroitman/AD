package com.example.loginclinicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroupAdp extends RecyclerView.Adapter<GroupAdp.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> data;


    //constructor
    GroupAdp(Context context, List<String> data){
        this.layoutInflater = layoutInflater.from(context);
        this.data = data;
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        String horario = data.get(i);
        viewHolder.txtHorario1.setText(horario);
        /*String dia = data.get(i);
        viewHolder.txtDiaTurno1.setText(dia);

        String mes = data.get(i);
        viewHolder.txtMesTurno1.setText(mes);

        String diaSemana = data.get(i);
        viewHolder.txtDiaSemanaTurno1.setText(diaSemana);



        String medico = data.get(i);
        viewHolder.txtMedico1.setText(medico);

        String especialidad = data.get(i);
        viewHolder.txtDiaTurno1.setText(especialidad);

        String estado = data.get(i);
        viewHolder.txtestado4.setText(estado);

        String asistencia = data.get(i);
        viewHolder.txtasistencia4.setText(asistencia);*/

        //me faltarian poner las imagenes.

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

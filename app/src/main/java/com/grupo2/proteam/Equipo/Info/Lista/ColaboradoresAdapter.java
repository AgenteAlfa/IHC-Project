package com.grupo2.proteam.Equipo.Info.Lista;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.grupo2.proteam.FStore.PrivadoUsuario;
import com.grupo2.proteam.R;

import java.util.List;

public class ColaboradoresAdapter extends BaseExpandableListAdapter {

        private final List<PrivadoUsuario> Colaboradores;

        public ColaboradoresAdapter(List<PrivadoUsuario> colaboradores) {
            Colaboradores = colaboradores;
        }

        @Override
        public Object getChild(int listPosition, int expandedListPosition) {
            //return Equipos.get(Deportes.get(listPosition)).get(expandedListPosition);
            return Colaboradores.get(expandedListPosition);
        }

        @Override
        public long getChildId(int listPosition, int expandedListPosition) {
            return expandedListPosition;
        }

        @Override
        public View getChildView(int listPosition, final int expandedListPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String NombreSubItem_Colaborador = Colaboradores.get(expandedListPosition).getNyA();
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.elstitem_colaboradorescontenido,parent, false);
            /*
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            */
            }
            TextView txtNyaColaborador = convertView .findViewById(R.id.subItem_txtNyaColaborador);
            txtNyaColaborador.setText(NombreSubItem_Colaborador);
            Button Eliminar = convertView.findViewById(R.id.subItem_btnEliminar);

            Eliminar.setOnClickListener(null);

            return convertView;
        }

        @Override
        public int getChildrenCount(int listPosition) {
            //return this.Equipos.get(this.Deportes.get(listPosition)).size();
            return Colaboradores.size();
        }

        @Override
        public Object getGroup(int listPosition) {
            //return this.Deportes.get(listPosition);
            return null;
        }

        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public long getGroupId(int listPosition) {
            return listPosition;
        }

        @Override
        public View getGroupView(int listPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
            /*LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
            */
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.elstitem_colaboradorestitulo,parent, false);
            }
            TextView TituloItem = convertView.findViewById(R.id.item_txtDeporte);
            TituloItem.setTypeface(null, Typeface.BOLD);
            String titulo = "Hay " + Colaboradores.size() + " registrados";
            TituloItem.setText(titulo);
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int listPosition, int expandedListPosition) {
            return true;
        }
}

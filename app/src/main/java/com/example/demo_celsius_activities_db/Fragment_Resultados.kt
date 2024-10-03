package com.example.demo_celsius_activities_db

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment_Resultados : Fragment() {

    private lateinit var recyclerView: RecyclerView // RecyclerView para mostrar resultados
    private val adapter = ResultadosAdapter() // Instancia del adaptador

    // Método llamado para crear la vista del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment__resultados, container, false)?.apply {
            recyclerView = findViewById(R.id.recyclerView) // Inicializar el RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Configurar el layout manager
            recyclerView.adapter = adapter // Configurar el adaptador

            // Obtener los resultados de la base de datos
            val dbHelper = DatabaseHelper(requireContext())
            val results = dbHelper.getAllResults() // Obtener datos de la base de datos
            adapter.setData(results) // Pasar los datos al adaptador
        }
    }
}
class ResultadosAdapter(private val resultList: MutableList<Pair<String, Double>> = mutableListOf()) : RecyclerView.Adapter<ResultadosAdapter.ResultadoViewHolder>() {

    // ViewHolder para los resultados
    inner class ResultadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val resultadoTextView: TextView = itemView.findViewById(R.id.resultadoTextView) // TextView para mostrar el resultado
    }

    // Método para crear la vista del ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultadoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_resultado, parent, false) // Inflar el layout del item
        return ResultadoViewHolder(itemView) // Devolver el ViewHolder
    }

    @SuppressLint("SetTextI18n")
    // Método para enlazar los datos al ViewHolder
    override fun onBindViewHolder(holder: ResultadoViewHolder, position: Int) {
        val (conversion, result) = resultList[position]  // Obtener el par de datos
        // Configurar el texto del TextView
        holder.resultadoTextView.text = "Tipo: $conversion, Resultado: $result"
    }

    // Método para obtener la cantidad de elementos
    override fun getItemCount(): Int {
        return resultList.size
    }

    // Método para actualizar los datos del adaptador
    fun setData(data: List<Pair<String, Double>>) {  // Cambiado para Pair
        resultList.clear() // Limpiar la lista actual
        resultList.addAll(data) // Agregar nuevos datos
        notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
    }
}
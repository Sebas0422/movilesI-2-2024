package com.example.practico1.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practico1.R
import com.example.practico1.models.Nota

class NotaAdapter(
    private val notaList: ArrayList<Nota>,
    private val colors: MutableMap<Int,Int>,
    private val listener: OnNotaClickListener
): RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view =
            LayoutInflater.from(parent.context).
            inflate(R.layout.nota_item_layout, parent, false)
        return NotaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notaList.size
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val color = colors[position] ?: Color.WHITE
        holder.bind(notaList[position], color, listener)
    }

    fun itemAdded(nota: Nota) {
        notaList.add(1, nota)
        notifyItemInserted(1)
    }

    fun itemUpdated(nota: Nota) {
        val index = getIndex(nota)
        notaList[index] = nota
        notifyItemChanged(index)
    }

    fun updatedBackgroundColor(position: Int, color: Int) {
        colors[position] = color
        notifyItemChanged(position)
    }

    fun itemDelete(nota: Nota) {
        val index = getIndex(nota)
        notaList.removeAt(index)
        notifyItemRemoved(index)
    }

    private fun getIndex(nota: Nota): Int {
        val foundData = notaList.first { it.id == nota.id }
        return notaList.indexOf(foundData)
    }

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var lblNotaItemTitle = itemView.findViewById<TextView>(R.id.txtNotaItemTitulo)
    private var lblNotaItemContent = itemView.findViewById<TextView>(R.id.txtNotaItemContenido)
    private var btnEditNotaItem = itemView.findViewById<ImageView>(R.id.btnEditNotaItem)
    private var btnDeleteNotaItem = itemView.findViewById<ImageView>(R.id.btnDeleteNotaItem)
    private var btnColorNotaItem = itemView.findViewById<ImageView>(R.id.btnColorNotaItem)

    fun bind(nota: Nota, color: Int, listener: OnNotaClickListener) {
        lblNotaItemTitle.text = nota.titulo
        lblNotaItemContent.text = nota.contenido
        btnEditNotaItem.setOnClickListener {
            listener.onNotaEditClickListener(nota)
        }
        btnDeleteNotaItem.setOnClickListener {
            listener.onNotaDeleteClickListener(nota)
        }
        btnColorNotaItem.setOnClickListener {
            listener.onNotaUpdateBackgroundColor(adapterPosition, color)
        }
        itemView.setBackgroundColor(color)
    }
}
    public interface OnNotaClickListener{
        fun onNotaEditClickListener(nota: Nota)
        fun onNotaDeleteClickListener(nota: Nota)
        fun onNotaUpdateBackgroundColor(position: Int, color: Int)
    }

}
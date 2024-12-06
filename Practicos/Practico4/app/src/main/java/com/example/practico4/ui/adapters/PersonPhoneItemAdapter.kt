package com.example.practico4.ui.adapters

import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.practico4.databinding.PersonPhoneItemLayoutBinding
import com.example.practico4.models.Person
import com.example.practico4.models.Phone

class PersonPhoneItemAdapter(
    private val phones: ArrayList<Phone>,
    private val listener : PersonPhoneItemListener
) : RecyclerView.Adapter<PersonPhoneItemAdapter.PersonPhoneItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonPhoneItemViewHolder {
        return PersonPhoneItemViewHolder(
            PersonPhoneItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return phones.size
    }

    override fun onBindViewHolder(holder: PersonPhoneItemViewHolder, position: Int) {
        holder.bind(phones[position], listener)
    }

    fun addPhone(phone: Phone) {
        phones.add(0,phone)
        notifyItemInserted(0)
    }

    private fun getIndex(phone: Phone): Int {
        val foundData = phones.first { it.id == phone.id }
        return phones.indexOf(foundData)
    }

    fun deleteItem(phone: Phone) {
        val index = getIndex(phone)
        phones.removeAt(index)
        notifyItemRemoved(index)
    }

    class PersonPhoneItemViewHolder(binding: PersonPhoneItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var optionsLabelPhone : Spinner = binding.OptionsLabelPhone
        private var txtPhone: EditText = binding.txtPhone
        private var btnDeletePhone : ImageView = binding.btnDeletePhone

        fun bind(phone: Phone, listener: PersonPhoneItemListener) {
            listener.addPhoneList(phone)
            txtPhone.addTextChangedListener(listener.onPhoneTextChanged(phone))
            if(phone.number.isNotEmpty()){
                txtPhone.setText(phone.number)

            }else{
                txtPhone.setHint("Ingrese el numero de telefono")
            }
            val options = mutableListOf( "Casa", "Trabajo", "Celular","Otro")
            addSpinnerOptions(phone.label, options)
            listener.onPhoneOptionsListener(phone,options, optionsLabelPhone)
            btnDeletePhone.setOnClickListener{listener.onPhoneDelete(phone)}
        }

        private fun addSpinnerOptions(select: String, options: MutableList<String>){
            val adapter = ArrayAdapter(itemView.context, android.R.layout.simple_spinner_dropdown_item, options)
            optionsLabelPhone.adapter = adapter
            // Si el select ya está en la lista, selecciona esa opción
            if (options.contains(select)) {
                optionsLabelPhone.setSelection(options.indexOf(select))
            } else if(select.isNotEmpty()) {
                // Si el select no está en la lista, añádelo y selecciónalo
                options.add(select)
                adapter.notifyDataSetChanged()
                optionsLabelPhone.setSelection(options.indexOf(select))
            }
        }
    }

    interface PersonPhoneItemListener {
        fun onPhoneOptionsListener(phone: Phone,options: MutableList<String>, optionsLabelPhone : Spinner)
        fun onPhoneTextChanged(phone:Phone):TextWatcher
        fun addPhoneList(phone: Phone)
        fun onPhoneDelete(phone: Phone)
    }
}
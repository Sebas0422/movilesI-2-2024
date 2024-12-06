package com.example.practico4.ui.adapters

import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.practico4.databinding.PersonEmailItemLayoutBinding
import com.example.practico4.models.Email

class PersonEmailItemAdapter (
    private val emails: ArrayList<Email>,
    private val listener : PersonEmailItemListener
) : RecyclerView.Adapter<PersonEmailItemAdapter.PersonEmailItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonEmailItemViewHolder {
        return PersonEmailItemViewHolder(
            PersonEmailItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return emails.size
    }

    override fun onBindViewHolder(holder: PersonEmailItemViewHolder, position: Int) {
        holder.bind(emails[position], listener)
    }

    fun addEmail(email: Email) {
        emails.add(0,email)
        notifyItemInserted(0)
    }

    private fun getIndex(email: Email): Int {
        val foundData = emails.first { it.id == email.id }
        return emails.indexOf(foundData)
    }

    fun deleteItem(email: Email) {
        val index = getIndex(email)
        emails.removeAt(index)
        notifyItemRemoved(index)
    }

    class PersonEmailItemViewHolder(binding: PersonEmailItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var optionsLabelEmail : Spinner = binding.OptionsLabelEmail
        private var txtEmail: EditText = binding.txtEmail
        private var btnDeleteEmail : ImageView = binding.btnDeleteEmail

        fun bind(email: Email, listener: PersonEmailItemListener) {
            listener.addEmailList(email)
            txtEmail.addTextChangedListener(listener.onEmailTextChanged(email))
            if(email.email.isNotEmpty()){
                txtEmail.setText(email.email)

            }else{
                txtEmail.setHint("Ingrese el numero de telefono")
            }
            val options = mutableListOf( "Casa", "Trabajo", "Celular","Otro")
            addSpinnerOptions(email.label, options)
            listener.onEmailOptionsListener(email,options, optionsLabelEmail)
            btnDeleteEmail.setOnClickListener{listener.onEmailDelete(email)}
        }

        private fun addSpinnerOptions(select: String, options: MutableList<String>){
            val adapter = ArrayAdapter(itemView.context, android.R.layout.simple_spinner_dropdown_item, options)
            optionsLabelEmail.adapter = adapter
            if (options.contains(select)) {
                optionsLabelEmail.setSelection(options.indexOf(select))
            } else if(select.isNotEmpty()) {
                options.add(select)
                adapter.notifyDataSetChanged()
                optionsLabelEmail.setSelection(options.indexOf(select))
            }
        }
    }

    interface PersonEmailItemListener {
        fun onEmailOptionsListener(email: Email, options: MutableList<String>, optionsLabelEmail : Spinner)
        fun onEmailTextChanged(email: Email): TextWatcher
        fun addEmailList(email: Email)
        fun onEmailDelete(email: Email)
    }
}
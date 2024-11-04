package com.example.practico4.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practico4.databinding.PersonItemLayoutBinding
import com.example.practico4.models.Person
import com.example.practico4.models.Persons
import com.example.practico4.models.Phone

class PersonItemAdapter(
    private val listener: PersonItemListener
) : RecyclerView.Adapter<PersonItemAdapter.PersonItemViewHolder>() {
    private var personList: ArrayList<Person> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonItemViewHolder {
        return PersonItemViewHolder(
            PersonItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonItemViewHolder, position: Int) {
        holder.bind(personList[position], listener)
    }

    fun updateData(it: Persons) {
        personList = it
        notifyDataSetChanged()
    }

    private fun getIndex(person: Person): Int {
        val foundData = personList.first { it.id == person.id }
        return personList.indexOf(foundData)
    }

    fun deletePerson(person: Person) {
        val index = getIndex(person)
        personList.removeAt(index)
        notifyItemRemoved(index)
    }

    class PersonItemViewHolder(binding: PersonItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val imgPerson: ImageView = binding.imgContact
        private val lblPersonName: TextView = binding.lblContactName
        private val btnDelete : ImageView = binding.btnDelete

        fun bind(person: Person, listener: PersonItemListener) {
            itemView.setOnClickListener {
                listener.onPersonItemClick(person)
            }
            btnDelete.setOnClickListener {
                listener.onPersonItemDelete(person)
            }
            lblPersonName.text = person.name
            if (person.profile_picture.isNotEmpty()){
                val url = person.profile_picture
                Glide.with(itemView.context)
                    .load(url)
                    .into(imgPerson)
            }

        }
    }

    interface PersonItemListener {
        fun onPersonItemClick(person: Person)
        fun onPersonItemDelete(person: Person)
    }
}
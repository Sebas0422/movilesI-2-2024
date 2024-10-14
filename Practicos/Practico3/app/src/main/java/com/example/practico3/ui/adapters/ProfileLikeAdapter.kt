package com.example.practico3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico3.databinding.ProfileLikeItemBinding
import com.example.practico3.models.User

class ProfileLikeAdapter(
    private var user: ArrayList<User>
) : RecyclerView.Adapter<ProfileLikeAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileLikeAdapter.ViewHolder {
        return ViewHolder(
            ProfileLikeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProfileLikeAdapter.ViewHolder, position: Int) {
        val item = user[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return user.size
    }

    fun itemAdded(user: User) {
        this.user.add(0, user)
        notifyItemInserted(0)
    }

    inner class ViewHolder(binding: ProfileLikeItemBinding): RecyclerView.ViewHolder(binding.root){
        private val profilePhoto = binding.photoUserLike
        private val lblFullName = binding.lblFullNameLike
        fun bind(item: User){
            profilePhoto.setImageResource(item.profilePhoto)
            lblFullName.text = item.fullName
        }
    }
}
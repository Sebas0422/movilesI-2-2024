package com.example.practico3.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico3.databinding.FragmentProfileBinding
import com.example.practico3.databinding.ProfileItemLayoutBinding
import com.example.practico3.models.User

class ProfileAdapter(
    private var users: ArrayList<User>,
    private val listener: OnProfileClickListener
) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProfileItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = users[position]
        holder.bind(item, listener)
    }

    fun itemDelete(user: User) {
        val index = getIndex(user)
        users.removeAt(index)
        notifyItemRemoved(index)
    }


    private fun getIndex(user: User): Int {
        val foundData = users.first { it.id == user.id }
        return users.indexOf(foundData)
    }


    inner class ViewHolder(binding: ProfileItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        private val line: LinearLayout = binding.line
        private val rvPhotos = binding.rvPhotos
        private val profilePhoto = binding.profilePhoto
        private val lblFullName = binding.lblFullName
        private val btnLike = binding.btnLike
        private val btnDislike = binding.btnDislike


        fun bind(item: User, listener: OnProfileClickListener){
            setupIndicator(line, item.photos.size)
            setupRecyclerView(item)
            profilePhoto.setImageResource(item.profilePhoto)
            lblFullName.text = item.fullName
            btnLike.setOnClickListener { listener.onLikeClick(item) }
            btnDislike.setOnClickListener { listener.onDislikeClick(item) }
        }

        private fun setupIndicator(linearLayout: LinearLayout, photoCount: Int) {
            for (i in 0 until photoCount) {
                val line = View(linearLayout.context).apply {
                    layoutParams = LinearLayout.LayoutParams(0, 10).apply {
                        weight = 4f
                        rightMargin = 20
                    }
                    setBackgroundColor(Color.BLACK)
                }
                linearLayout.addView(line)
            }
        }

        private fun setupRecyclerView(item: User) {
            rvPhotos.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            rvPhotos.adapter = PhotoAdapter(item.photos)
            rvPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    updateIndicator()
                }
            })
        }

        private fun updateIndicator() {
            val layoutManager = rvPhotos.layoutManager as LinearLayoutManager
            val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
            for (i in 0 until line.childCount) {
                val line = line.getChildAt(i)
                line.setBackgroundColor(if (i == firstVisiblePosition) Color.BLUE else Color.GRAY)
            }
        }

    }

    public interface OnProfileClickListener{
        fun onLikeClick(user: User)
        fun onDislikeClick(user: User)
    }

}
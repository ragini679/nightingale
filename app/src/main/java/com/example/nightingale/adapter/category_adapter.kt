package com.example.nightingale.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.nightingale.api_interface
import com.example.nightingale.databinding.StrOfArtistBinding
import com.example.nightingale.databinding.StrOfCategoryBinding
import com.example.nightingale.models.Artiest
import com.example.nightingale.models.Categary
import com.example.nightingale.music_list
import com.example.nightingale.my_music_data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class category_adapter(private val categoryList: List<Categary>): RecyclerView.Adapter<category_adapter.Myviewholder>() {
    class Myviewholder(private val binding:StrOfCategoryBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindingview(category: Categary) {
            binding.catTextView.text = category.name
            Glide.with(binding.catImageView).load(category.coverurl)
                .apply(
                    RequestOptions().transform(RoundedCorners(32))
                )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.catImageView)
            val context = binding.root.context
            binding.root.setOnClickListener {
                binding.root.setOnClickListener {
                    music_list.category=category
                    when (category.name) {
                        "English music" -> {
                            fetchDeezerMusic("eminem", context)
                        }

                        "Folk Music" -> {

                        }

                        "Hindi" -> {

                        }

                        else -> {
                            // Handle other categories
                        }
                    }
                }
            }
        }
        private fun fetchDeezerMusic(query: String, context: Context) {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api_interface:: class.java)

            val retrofitData = retrofitBuilder.getData(query)
            retrofitData.enqueue(object: Callback<my_music_data?> {
                override fun onResponse(call: Call<my_music_data?>, response: Response<my_music_data?>) {
                    val dataList=response.body()?.data!!
                    val intent = Intent(context, music_list::class.java)
                    intent.putParcelableArrayListExtra("musicList", ArrayList(dataList))
                    context.startActivity(intent)
                }
                override fun onFailure(call: Call<my_music_data?>, t: Throwable) {
                    Log.d("tag:onfailure","onfailure:"+ t.message)
                }

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val binding=StrOfCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return Myviewholder(binding)
    }

    override fun getItemCount(): Int {
      return categoryList.size
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
         holder.bindingview(categoryList[position])

    }
}
class Artiest_adapter(private val artiestList: List<Artiest>): RecyclerView.Adapter<Artiest_adapter.viewholder>() {
    class viewholder(private val binding:StrOfArtistBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindingview(category: Artiest) {
            binding.artTextView.text = category.art_name
            Glide.with(binding.artImageView).load(category.art_img)
                .apply(
                    RequestOptions().transform(RoundedCorners(32))
                )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.artImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):viewholder {
        val binding=StrOfArtistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewholder(binding)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bindingview(artiestList[position])
    }

    override fun getItemCount(): Int {
        return artiestList.size
    }
}
package com.example.nightingale

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.nightingale.adapter.music_list_adapter
import com.example.nightingale.databinding.ActivityMusicListBinding
import com.example.nightingale.models.Categary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class music_list : AppCompatActivity() {
    companion object{
        lateinit var category: Categary
    }
    lateinit var binding:ActivityMusicListBinding
   lateinit var  musicListAdapter: music_list_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMusicListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.catTextView.text=category.name
        Glide.with(binding.catImageView).load(category.coverurl)
            .apply(
                RequestOptions().transform(RoundedCorners(32))
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.catImageView)
        @Suppress("DEPRECATION")
        val musicList: List<Data> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("musicList") as? ArrayList<Data>?: emptyList()
        } else {
            intent.getParcelableArrayListExtra<Data>("musicList")?: emptyList()
        }
        musicListAdapter= music_list_adapter(this@music_list,musicList)
        binding.musicRecyclerview.adapter=musicListAdapter
        binding.musicRecyclerview.layoutManager= LinearLayoutManager(this@music_list)
    }
}
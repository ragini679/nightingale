package com.example.nightingale

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nightingale.adapter.Artiest_adapter
import com.example.nightingale.adapter.category_adapter
import com.example.nightingale.adapter.music_list_adapter
import com.example.nightingale.databinding.ActivityMainBinding
import com.example.nightingale.models.Artiest
import com.example.nightingale.models.Categary
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
   lateinit var  categoryadapter:category_adapter
    lateinit var  Artiestadapter: Artiest_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
          Categories()
          Artiest();
    }
    fun Categories(){
        FirebaseFirestore.getInstance().collection("Category")
            .get().addOnSuccessListener(){
                val categorylist=it.toObjects(Categary::class.java)
                setuprecyclerview(categorylist)
            }
    }
    fun Artiest(){
        FirebaseFirestore.getInstance().collection("Artiest")
            .get().addOnSuccessListener(){
                val artiestlist=it.toObjects(Artiest::class.java)
                setupartiestrecyclerview(artiestlist)
            }
    }
    fun setuprecyclerview(categorylist:List<Categary>){
        categoryadapter=category_adapter(categorylist)
        binding.catRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.catRecyclerView.adapter=categoryadapter
    }
    fun setupartiestrecyclerview(artiestlist:List<Artiest>){
        Artiestadapter=Artiest_adapter(artiestlist)
        binding.catArtiestView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.catArtiestView.adapter=Artiestadapter
    }

}
package com.jantonioc.lab7recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jantonioc.lab7recyclerview.data.DogsAdapter
import com.jantonioc.lab7recyclerview.dto.DogResponse
import com.jantonioc.lab7recyclerview.network.ApiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var etraza: EditText
    private lateinit var btnBuscar: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var myRecyclerView: RecyclerView
    private var adapter:DogsAdapter?= null
    private var dogsList:List<String> = ArrayList()
    private var layoutManager:RecyclerView.LayoutManager?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etraza = findViewById(R.id.etRaza)
        btnBuscar = findViewById(R.id.btnBuscar)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.INVISIBLE
        myRecyclerView = findViewById(R.id.myReciclerView)

        btnBuscar.setOnClickListener {
            searchDogs()
        }
    }

    fun searchDogs() {
        if(etraza.text.toString().isEmpty())
        {
            Toast.makeText(this,"Debe Digitar una raza",Toast.LENGTH_SHORT).show()
            return
        }

        progressBar.visibility = View.VISIBLE

           launch{
               try{
                   val apiResponse = ApiAdapter.apiClient.getListOfPet(etraza.text.toString())
                   if (apiResponse.isSuccessful && apiResponse.body() != null) {
                       progressBar.visibility = View.INVISIBLE
                       val dogs = apiResponse.body() as DogResponse
                       initRecycler(dogs)
                       Log.v("APIDATA", "Data: ${dogs}")
                   }else{
                       progressBar.visibility = View.INVISIBLE
                       Log.v("APIDATA", "No se encontro esa raza")
                   }

               }catch (e: Exception){
                   progressBar.visibility = View.INVISIBLE
                   Log.v("APIDATA", "Exception: ${e.localizedMessage}")
               }
           }
    }

    private fun initRecycler(dogs: DogResponse) {
        if(dogs.status=="success"){
            dogsList = dogs.images
        }

        layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager

        adapter = DogsAdapter(dogsList, this)
        myRecyclerView.adapter = adapter

    }
}
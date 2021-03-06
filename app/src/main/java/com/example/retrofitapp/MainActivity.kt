package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitapp.remote.PokemonEntry
import com.example.retrofitapp.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create().getPokemonById("27")

        retrofit.enqueue(object: Callback<PokemonEntry> {
            override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>){
                val resBody = response.body()
                if(resBody != null){
                    Log.d("retrofitresponse", "front_default: ${resBody.sprites.front_default}")
                    Log.d("retrofitresponse", "name: ${resBody.name}")
                    val typesArray = resBody.types
                    val type = typesArray[0].type.name
                    Log.d("retrofitresponse", "type: ${type}")
                    val stats = resBody.stats
                    for (stat in stats){
                        Log.d("retrofitresponse", "${stat.stat.name}: ${stat.base_stat}")
                    }
                }
            }

            override fun onFailure(call: Call<PokemonEntry>, t: Throwable) {
                Log.e("retrofitresponse", "error: ${t.message}")
            }
        })

    }
}
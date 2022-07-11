package com.example.fotos_recyclerview

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fotos.view.*
import kotlinx.android.synthetic.main.main2.*


class MainActivity : AppCompatActivity() {

lateinit var fotos_galeria:LiveAdapter
var codigo_request = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
setContentView(R.layout.main2)
        permission()

    }

    fun permission(){
        if(ActivityCompat.checkSelfPermission
                (this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        { ActivityCompat.requestPermissions(this@MainActivity,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE ),codigo_request) }
        else{

            get_fotos()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(grantResults[0] == 0) {
            get_fotos()
        }
        else{
            Toast.makeText(this@MainActivity,"Sem a permissao o app nao pode funcionar, conceda as permissoes para prosseguir",Toast.LENGTH_LONG).show()
        }
    }


    fun get_fotos(){
       // get_fotos pega o Uri das fotos do usuario
        var list_fotos = arrayListOf<String>()

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                var columnfotos:Int ?= null
                var absolutePathfoto:String ?= null

                var uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                var projection = arrayOf(
                    MediaStore.Images.Media.DATA
                )
                var orderBy:String = MediaStore.Images.Media.DATE_TAKEN
                var cursor = applicationContext.contentResolver.query(
                    uri,
                    projection,
                    null,
                    null,
                    "$orderBy DESC"
                )

                columnfotos = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

                while (cursor?.moveToNext()==true){
                    absolutePathfoto = cursor!!.getString(columnfotos!!)
                    list_fotos.add(absolutePathfoto)

                }
            }else{
                Toast.makeText(this@MainActivity,"else error",Toast.LENGTH_LONG).show()
            }

        }catch (e:Exception){
            Toast.makeText(this@MainActivity,"get fos erro",Toast.LENGTH_LONG).show()
            Log.e("ERROR", e.message?:"")
        }

        fotos_galeria  = LiveAdapter(list_fotos)
        fotos_galeria!!.setList(list_fotos)
        initRecyclerView()

    }

    private fun initRecyclerView() {

        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            adapter = fotos_galeria

        }

    }

}
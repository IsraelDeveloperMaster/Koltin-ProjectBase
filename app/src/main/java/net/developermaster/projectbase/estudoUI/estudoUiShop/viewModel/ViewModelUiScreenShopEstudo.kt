/*
 * *
 *  * Created by rael on 20/02/2025 22:30
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 20/02/2025 21:52
 *
 */

package net.developermaster.projectbase.estudoUI.estudoUiShop.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopBanner
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopCategoria
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopItems

class ViewModelUiScreenShopEstudo : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _categoria = MutableLiveData<MutableList<ModelUiScreenShopCategoria>>()
    val categoria: LiveData<MutableList<ModelUiScreenShopCategoria>> = _categoria

    private val _banner = MutableLiveData<List<ModelUiScreenShopBanner>>()
    val banner: LiveData<List<ModelUiScreenShopBanner>> = _banner

    private val _recommended = MutableLiveData<MutableList<ModelUiScreenShopItems>>()
    val recommended: LiveData<MutableList<ModelUiScreenShopItems>> = _recommended

    fun loadBanner() {

        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val lista = mutableListOf<ModelUiScreenShopBanner>()

                for (children in snapshot.children) {

                    val model = children.getValue(ModelUiScreenShopBanner::class.java)

                    if (model != null) {

                        lista.add(model)
                    }
                }
                _banner.value = lista

                /*                Log.d("TAGBANNER", "VIEWMODEL: ${lista[0].url}")
                                Log.d("TAGBANNER", "VIEWMODEL: ${lista[1].url}")*/
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun loadCategoria() {

        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val lista = mutableListOf<ModelUiScreenShopCategoria>()

                for (children in snapshot.children) {

                    val model = children.getValue(ModelUiScreenShopCategoria::class.java)

                    if (model != null) {

                        lista.add(model)
                    }
                }
                _categoria.value = lista
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun loadRecommended() {

        val ref = firebaseDatabase.getReference("Items")
        val query : Query = ref.orderByChild("showRecommended").equalTo(true)
        query.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val lista = mutableListOf<ModelUiScreenShopItems>()

                for (children in snapshot.children) {

                    val model = children.getValue(ModelUiScreenShopItems::class.java)

                    if (model != null) {

                        lista.add(model)
                    }
                }
                _recommended.value = lista
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun loadFiltred() {

        val ref = firebaseDatabase.getReference("Items")
        val query : Query = ref.orderByChild("showRecommended").equalTo(true)
        query.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val lista = mutableListOf<ModelUiScreenShopItems>()

                for (children in snapshot.children) {

                    val model = children.getValue(ModelUiScreenShopItems::class.java)

                    if (model != null) {

                        lista.add(model)
                    }
                }
                _recommended.value = lista
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
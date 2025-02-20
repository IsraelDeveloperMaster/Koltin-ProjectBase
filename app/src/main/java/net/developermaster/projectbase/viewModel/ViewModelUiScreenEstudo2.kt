package net.developermaster.projectbase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import net.developermaster.projectbase.model.ModelUiScreenShopBanner

class ViewModelUiScreenEstudo2 : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableStateFlow<List<ModelUiScreenShopBanner>>(emptyList())

    val banner: LiveData<List<ModelUiScreenShopBanner>> = _banner.asLiveData()

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

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
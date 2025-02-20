package net.developermaster.projectbase.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import net.developermaster.projectbase.model.ModelUiScreenEstudo

class ViewModelUiScreenEstudo1 : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<ModelUiScreenEstudo>>(emptyList())

    val banner: LiveData<List<ModelUiScreenEstudo>> = _banner

    fun loadBanner() {

        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val lista = mutableListOf<ModelUiScreenEstudo>()

                for (children in snapshot.children) {

                    val model = children.getValue(ModelUiScreenEstudo::class.java)

                    if (model != null) {

                        lista.add(model)
                    }
                }

                _banner.value = lista

                Log.d("TAGBANNER", "VIEWMODEL: ${lista[0].url}")
                Log.d("TAGBANNER", "VIEWMODEL: ${lista[1].url}")

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
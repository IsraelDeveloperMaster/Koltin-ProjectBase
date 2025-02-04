package net.developermaster.projectbase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import net.developermaster.projectbase.model.Model
import net.developermaster.projectbase.repository.Repository

class ViewModelProjectBase() : ViewModel() {

    private val repository = Repository()

    init {
        // Inicializar
    }

    fun viewModelSalvar(model: Model, onResult: (Boolean) -> Unit) {
        repository.repositorySalvar(model, onResult)
    }

    fun viewModelListar(documentId: String, onResult: (Model?) -> Unit) {
        repository.repositoryListar(documentId, onResult)
    }

    suspend fun viewModelListarSuspend(id: String): Model? {
        return repository.repositoryListarSuspend(id)
    }

    fun viewModelSalvarLogs(model: Model, onResult: (Boolean) -> Unit) {
        repository.repositorySalvarLogs(model, onResult)
    }
}
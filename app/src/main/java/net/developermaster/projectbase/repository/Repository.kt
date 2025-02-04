package net.developermaster.projectbase.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.developermaster.projectbase.model.Model

import kotlin.jvm.java

class Repository {

    // Salvar atualizar o estado dos switches planta 0
    fun repositorySalvar(model: Model, onResult: (Boolean) -> Unit) {

        FirebaseFirestore.getInstance().collection("projetoBase").document(model.id) .set(model)

            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    // metodo salvar
    fun repositoryListar(documentId: String, onResult: (Model?) -> Unit) {

        FirebaseFirestore.getInstance().collection("projetoBase").document(documentId).get()

            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val modelSwitch = document.toObject(Model::class.java)
                    onResult(modelSwitch)
                } else {
                    onResult(null)
                }
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    // metodo listar suspend
    suspend fun repositoryListarSuspend(id: String): Model? {
        return try {
            val document =
                FirebaseFirestore.getInstance().collection("projetoBase").document(id).get().await()

            document.toObject(Model::class.java)

        } catch (e: Exception) {
            null
        }
    }

    // Metodo para salvar o ModelLogs
    fun repositorySalvarLogs(model: Model, onResult: (Boolean) -> Unit) {

        FirebaseFirestore.getInstance().collection("projetoBase").document(model.id) .set(model)

            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }
}

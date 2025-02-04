package net.developermaster.projectbase.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.tasks.await
import net.developermaster.projectbase.model.Model

import kotlin.jvm.java

class Repository {

    val context = ApplicationProvider.getApplicationContext<Context>()

    // metodo salvar
    fun repositorySalvar(model: Model, onResult: (Boolean) -> Unit) {

        FirebaseFirestore.getInstance().collection("projetoBase").document(model.id).set(model)

            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    // metodo listar
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

        FirebaseFirestore.getInstance().collection("projetoBase").document(model.id).set(model)

            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }


    //////////////////////////////////////////////////////////////////////////

    companion object {
        const val MIN_VERSION = "min_version"
    }

    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig.apply {
        setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 30 })
        fetchAndActivate()
    }

    suspend fun getMinAllowedVersion(): List<Int> {

        remoteConfig.fetch(0)
        remoteConfig.activate().await()

        val minVersion = remoteConfig.getString(MIN_VERSION)
        return if (minVersion.isBlank()) listOf(0, 0, 0)
        else minVersion.split(".").map { it.toInt() }
    }

    fun getCurrentVersion(): List<Int> {

        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName?.split(".")!!.map {
                it.toInt()

            }
        } catch (e: Exception) {
            listOf(0, 0, 0)
        }
    }

    /////////////////////////////////////////////////////////////////////////

}

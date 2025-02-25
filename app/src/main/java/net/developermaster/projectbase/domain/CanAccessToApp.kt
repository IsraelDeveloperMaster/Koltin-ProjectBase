package net.developermaster.projectbase.domain

import net.developermaster.projectbase.repository.Repository

class CanAccessToApp {

    val repository = Repository()

    suspend operator fun invoke(): Boolean {
        val currentVersion = repository.getCurrentVersion() //1.0.3
        val minAllowedVersion = repository.getMinAllowedVersion() //1.0.2

        for((currentPart, minVersionPart) in currentVersion.zip(minAllowedVersion)){
            if(currentPart!=minVersionPart){
                return currentPart > minVersionPart
            }
        }

        return true
    }
}
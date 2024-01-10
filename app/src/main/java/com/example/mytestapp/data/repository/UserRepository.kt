package com.example.mytestapp.data.repository

import com.example.mytestapp.data.model.UserEntity
import io.realm.Realm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepository {
    fun getUser(): Flow<List<UserEntity>>
    fun saveData(userEntity: List<UserEntity>): Flow<Unit>
    fun deleteDataByIds(ids: List<Int>): Flow<Unit>

}

class UserRepositoryImpl(
    private val realm: Realm,
) : UserRepository {

    override fun getUser(): Flow<List<UserEntity>> = flow {
        runCatching {
            realm.where(UserEntity::class.java).findAll()
        }.onSuccess { userLoginEntity ->
            emit(userLoginEntity)
        }.onFailure { exception ->
            throw exception
        }
    }

    override fun saveData(userEntity: List<UserEntity>): Flow<Unit> = flow {
        runCatching {
            realm.beginTransaction()
            realm.insertOrUpdate(userEntity)
            realm.commitTransaction()
        }.onSuccess {
            emit(Unit)
        }.onFailure { exception ->
            throw exception
        }
    }


    override fun deleteDataByIds(ids: List<Int>): Flow<Unit> = flow {
        val userEntities = realm.where(UserEntity::class.java).`in`("id", ids.toTypedArray()).findAll()
        runCatching {
            realm.beginTransaction()
            userEntities.deleteAllFromRealm()
            realm.commitTransaction()
        }.onSuccess {
            emit(Unit)
        }.onFailure { exception ->
            throw exception
        }
    }


}
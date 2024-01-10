package com.example.mytestapp.domain.usecase

import com.example.mytestapp.data.model.UserEntity
import com.example.mytestapp.data.repository.UserRepository
import io.realm.RealmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface DataItemUseCase {
    fun executeGetData(): Flow<List<UserEntity>>
    fun executeSaveData(item:String): Flow<Unit>
    fun executeDeleteDataByIds(item: List<Int>): Flow<Unit>
}

class DataItemUseCaseImpl(
    private val userRepository: UserRepository,
) : DataItemUseCase {
    private var currentId = 0
    override fun executeGetData(): Flow<List<UserEntity>> = flow {
        userRepository.getUser().collect {
            emit(it)
        }
    }

    override fun executeSaveData(item:String): Flow<Unit> = flow {
        currentId++
        val userList:   ArrayList<UserEntity> = arrayListOf()
        val userEntity = UserEntity(id = currentId, name = item)
        userList.add(userEntity)
        userRepository.saveData(userList).collect {
            emit(it)
        }
    }

    override fun executeDeleteDataByIds(item: List<Int>): Flow<Unit> = flow {
        userRepository.deleteDataByIds(item).collect {
            emit(it)
        }
    }




}
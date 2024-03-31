package com.hiltmvvm.notesample.repository

import androidx.lifecycle.MutableLiveData
import com.hiltmvvm.notesample.api.NoteAPI
import com.hiltmvvm.notesample.db.NotesDB
import com.hiltmvvm.notesample.models.NoteRequest
import com.hiltmvvm.notesample.models.NoteResponse
import com.hiltmvvm.notesample.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteAPI: NoteAPI, private val notesDB: NotesDB) {

    private val _notesFlow = MutableStateFlow<NetworkResult<List<NoteResponse>>>(NetworkResult.Loading())
    val notesFlow get() = _notesFlow

    private val _statusLiveData = MutableLiveData<NetworkResult<Pair<Boolean, String>>>()
    val statusLiveData get() = _statusLiveData

    suspend fun createNote(noteRequest: NoteRequest) {
        _statusLiveData.postValue(NetworkResult.Loading())
        val response = noteAPI.createNote(noteRequest)
        handleResponse(response, "Note Created")
    }

    suspend fun getNotes() {
        val response = noteAPI.getNotes()
        if (response.isSuccessful && response.body() != null) {
             notesDB.getNotesDAO().addNotes(response.body()!!)
            _notesFlow.emit(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _notesFlow.emit(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _notesFlow.emit(NetworkResult.Error("Something Went Wrong"))
        }
    }

    suspend fun updateNote(id: String, noteRequest: NoteRequest) {
        _statusLiveData.postValue(NetworkResult.Loading())
        val response = noteAPI.updateNote(id, noteRequest)
        handleResponse(response, "Note Updated")
    }

    suspend fun deleteNote(noteId: String) {
        _statusLiveData.postValue(NetworkResult.Loading())
        val response = noteAPI.deleteNote(noteId)
        handleResponse(response, "Note Deleted")
    }

    private fun handleResponse(response: Response<NoteResponse>, message: String) {
        if (response.isSuccessful && response.body() != null) {
            _statusLiveData.postValue(NetworkResult.Success(Pair(true, message)))
        } else {
            _statusLiveData.postValue(NetworkResult.Success(Pair(false, "Something went wrong")))
        }
    }
}
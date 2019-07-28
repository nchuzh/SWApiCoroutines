package com.nchuzh.swapikotlincoroutines.view.characterlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.nchuzh.swapikotlincoroutines.R
import com.nchuzh.swapikotlincoroutines.data.model.StarWarsCharacter
import com.nchuzh.swapikotlincoroutines.domain.CharactersRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CharacterListActivity : AppCompatActivity(), CharacterListView {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val repository : CharactersRepository = CharactersRepository()

    private var list:  List<StarWarsCharacter>? = null

    fun fetch(){
        scope.launch {
            list = repository.getCharacterList()
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetch()
    }
}

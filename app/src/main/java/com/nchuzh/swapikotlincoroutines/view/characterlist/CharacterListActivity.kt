package com.nchuzh.swapikotlincoroutines.view.characterlist

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nchuzh.swapikotlincoroutines.R
import com.nchuzh.swapikotlincoroutines.domain.repository.CharactersRepository
import com.nchuzh.swapikotlincoroutines.domain.model.CharacterDetails
import com.nchuzh.swapikotlincoroutines.domain.model.MovieCharacter
import com.nchuzh.swapikotlincoroutines.view.character.CharacterActivity
import com.nchuzh.swapikotlincoroutines.view.showProgressDialog
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.coroutines.*

class CharacterListActivity : AppCompatActivity(), CharacterListView {
    private val repository: CharactersRepository = CharactersRepository()
    private var list: List<MovieCharacter>? = null
    private lateinit var listProgress: ProgressDialog
    private lateinit var detailsProgress: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        character_list.layoutManager = LinearLayoutManager(this)
        character_list.adapter = CharacterAdapter(::openDetails)

        character_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager!!.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2) {
                    fetch()
                }
            }
        })

        fetch()
    }

    private fun fetch() {
        listProgress = showProgressDialog(this)
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacterList()
            withContext(Dispatchers.Main) {
                list = response
                (character_list.adapter as CharacterAdapter).addData(list!!)
                (character_list.adapter as CharacterAdapter).notifyDataSetChanged()
                listProgress.dismiss()
            }
        }
    }

    private fun openDetails(character: MovieCharacter) {
        detailsProgress = showProgressDialog(this)
        CoroutineScope(Dispatchers.IO).launch {
            val planet = repository.getPlanet(character.planetUrl)
            withContext(Dispatchers.Main) {
                val intent = Intent(this@CharacterListActivity, CharacterActivity::class.java)
                intent.putExtra(CharacterActivity.KEY, CharacterDetails(character, planet!!))
                detailsProgress.dismiss()
                startActivity(intent)
            }
        }
    }
}

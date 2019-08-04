package com.nchuzh.swapikotlincoroutines.view.characterlist

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nchuzh.swapikotlincoroutines.R
import com.nchuzh.swapikotlincoroutines.domain.model.CharacterDetails
import com.nchuzh.swapikotlincoroutines.domain.model.MovieCharacter
import com.nchuzh.swapikotlincoroutines.view.character.CharacterActivity
import com.nchuzh.swapikotlincoroutines.view.showProgressDialog
import kotlinx.android.synthetic.main.activity_list.*

class CharacterListActivity : AppCompatActivity(), CharacterListView {
    private val presenter = CharacterListPresenter(this)
    private lateinit var listProgress: ProgressDialog
    private lateinit var detailsProgress: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        character_list.layoutManager = LinearLayoutManager(this)
        character_list.adapter = CharacterAdapter(presenter::getDetails)

        character_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager!!.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2) {
                    presenter.fetch()
                }
            }
        })

        presenter.fetch()
    }

    override fun openDetails(details: CharacterDetails) {
        val intent = Intent(this@CharacterListActivity, CharacterActivity::class.java)
        intent.putExtra(CharacterActivity.KEY, details)
        startActivity(intent)
    }

    override fun hideDetailsProgress() {
        if (::detailsProgress.isInitialized) {
            detailsProgress.dismiss()
        }
    }

    override fun showDetailsProgress() {
        if (::detailsProgress.isInitialized) {
            detailsProgress.show()
        } else {
            detailsProgress = showProgressDialog(this)
        }
    }

    override fun hideProgress() {
        if (::listProgress.isInitialized) {
            listProgress.dismiss()
        }
    }

    override fun setList(list: List<MovieCharacter>?) {
        list?.let {
            (character_list.adapter as CharacterAdapter).addData(list)
            (character_list.adapter as CharacterAdapter).notifyDataSetChanged()
        }
    }

    override fun showProgress() {
        if (::listProgress.isInitialized) {
            listProgress.show()
        } else {
            listProgress = showProgressDialog(this)
        }
    }
}

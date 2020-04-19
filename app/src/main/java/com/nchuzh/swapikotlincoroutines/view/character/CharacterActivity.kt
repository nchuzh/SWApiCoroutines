package com.nchuzh.swapikotlincoroutines.view.character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nchuzh.swapikotlincoroutines.R
import com.nchuzh.swapikotlincoroutines.domain.model.CharacterDetails
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : AppCompatActivity(), CharacterView {
    private val presenter = CharacterPresenter(this)

    companion object {
        private val KEY = "character"

        fun createIntent(context: Context, details: CharacterDetails): Intent {
            val intent = Intent(context, CharacterActivity::class.java)
            intent.putExtra(KEY, details)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val data = intent.getParcelableExtra<CharacterDetails>(KEY)
        presenter.setData(data)
    }

    override fun showDetails(details: CharacterDetails) {
        txt_name.text = getString(R.string.placeholder_name).format(details.essentials.name)
        txt_birthDate.text =
            getString(R.string.placeholder_birth).format(details.essentials.birthDate)
        txt_gender.text = getString(R.string.placeholder_gender).format(details.essentials.gender)
        txt_planet.text = getString(R.string.placeholder_planet).format(details.planet)
    }
}
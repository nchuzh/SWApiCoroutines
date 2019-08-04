package com.nchuzh.swapikotlincoroutines.view.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nchuzh.swapikotlincoroutines.R
import com.nchuzh.swapikotlincoroutines.domain.model.CharacterDetails
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity: AppCompatActivity(), CharacterView {
    companion object {
        val KEY = "character"
    }

    private val presenter = CharacterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val data = intent.getParcelableExtra<CharacterDetails>(KEY)
        presenter.setData(data)
    }

    override fun showDetails(details: CharacterDetails) {
        txt_name.text = String.format("Name: %s", details.essentials.name)
        txt_birthDate.text = String.format("Birth date: %s", details.essentials.birthDate)
        txt_gender.text = String.format("Gender: %s", details.essentials.gender)
        txt_planet.text = String.format("Planet: %s", details.planet)
    }
}
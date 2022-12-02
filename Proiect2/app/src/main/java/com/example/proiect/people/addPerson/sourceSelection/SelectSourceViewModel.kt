package com.example.proiect.people.addPerson.sourceSelection

import androidx.lifecycle.ViewModel
import com.example.proiect.model.ActionOption

class SelectSourceViewModel: ViewModel() {
    fun getOptions(): List<ActionOption> = listOf(
        ActionOption("Cauta orice utilizator", "Cauta pe oricine, chiar daca nu il ai in contacte"),
        ActionOption("Cauta din telefon","Cauta doar din agenda ta telefonica"))
}


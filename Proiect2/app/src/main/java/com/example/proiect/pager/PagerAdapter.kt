package com.example.proiect.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.proiect.login.LoginFragment
import com.example.proiect.model.Option
import com.example.proiect.people.contacts.PeopleFragment

class PagerAdapter(fragment: Fragment
) : FragmentStateAdapter(fragment) {
    var options: List<Option> = emptyList()
    override fun getItemCount(): Int {
        return options.size
    }

    override fun createFragment(position: Int): Fragment =
        options[position].fragment
}
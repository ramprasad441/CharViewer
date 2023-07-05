package com.ramprasad.charviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.ramprasad.charviewer.R
import com.ramprasad.charviewer.adapter.CharactersAdapter
import com.ramprasad.charviewer.commons.StateOfResponse
import com.ramprasad.charviewer.databinding.FragmentCharactersListBinding
import com.ramprasad.charviewer.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Ramprasad on 7/4/23.
 */
@AndroidEntryPoint
class CharactersListFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentCharactersListBinding
    private val viewModel: CharactersViewModel by activityViewModels()

    private lateinit var adapter: CharactersAdapter
    private lateinit var search: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val menuHost: MenuHost = requireActivity()
            menuHost.addMenuProvider(this@CharactersListFragment, viewLifecycleOwner, Lifecycle.State.RESUMED)
            adapter = CharactersAdapter {
                viewModel.characterDetails.postValue(it)
                if (activity is MainActivity) {
                    (activity as MainActivity).findMainNavController()
                        .navigate(R.id.action_ListFragment_to_DetailFragment)
                }
            }
            rvCharacterNames.adapter = adapter
            viewModel.characters.observe(viewLifecycleOwner) {
                progressBar.isVisible = it is StateOfResponse.Loading
                when (it) {
                    is StateOfResponse.Loading -> {}
                    is StateOfResponse.Error -> {
                        Snackbar.make(slidingPaneLayout, it.msg, Toast.LENGTH_LONG).show()
                    }

                    is StateOfResponse.Success -> {
                        adapter.modifyList(it.data.relatedTopics)
                    }
                }
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                adapter.renderList()
                return true
            }
        })
        return true
    }

    override fun onPrepareMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.actionSearch)
        val searchView = searchItem.actionView as SearchView
        search(searchView)
        super.onPrepareMenu(menu)
    }

    private fun search(search: SearchView) {
        this.search = search
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.saveQuery(query)
                adapter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        if (viewModel.searchQuery.isNotEmpty()) {
            search.setQuery(viewModel.searchQuery, true)
        }
    }

    fun shouldGoBack(): Boolean {
        return if (viewModel.searchQuery.isNotEmpty()) {
            viewModel.saveQuery("")
            search.setQuery("", true)
            false
        } else {
            true
        }
    }

}
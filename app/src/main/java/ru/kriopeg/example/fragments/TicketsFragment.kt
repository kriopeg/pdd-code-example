package ru.kriopeg.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tickets.*
import ru.kriopeg.example.R
import ru.kriopeg.example.adapters.TicketAdapter
import ru.kriopeg.example.domain.models.Ticket
import ru.kriopeg.example.domain.usecases.GetTicketsUseCase
import ru.kriopeg.example.hide
import ru.kriopeg.example.show
import ru.kriopeg.example.viewmodels.TicketsViewModel

@AndroidEntryPoint
class TicketsFragment : Fragment(), TicketAdapter.OnItemClickListener {

    private val SPAN_COUNT = 2
    private val viewModel: TicketsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tickets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.ticketsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is GetTicketsUseCase.Result.Loading -> {
                    showProgress()
                }
                is GetTicketsUseCase.Result.Success -> {
                    showContent()
                    setupRecyclerView(it.tickets)
                }
                is GetTicketsUseCase.Result.Empty -> {
                    showEmptyMessage()
                }
            }
        }
    }

    private fun showContent() {
        progress_bar.hide()
        empty_message_text_view.hide()
        recycler_view.show()
    }

    private fun showProgress() {
        progress_bar.show()
        empty_message_text_view.hide()
        recycler_view.hide()
    }

    private fun showEmptyMessage() {
        progress_bar.hide()
        empty_message_text_view.show()
        recycler_view.hide()
    }

    // Несколько функций расположенных выше можно записать более компактно, как я сделал ниже
    // Но это не так читабельно, а в некоторых случаях неприменимо

    /*private fun initViewModel() {
        viewModel.ticketsLiveData.observe(viewLifecycleOwner) {
            setupViewState(it)
            if (it is GetTicketsUseCase.Result.Success) {
                setupRecyclerView(it.tickets)
            }
        }
    }

    private fun setupViewState(result: GetTicketsUseCase.Result) {
        progress_bar.toggleVisibility(result is GetTicketsUseCase.Result.Loading)
        empty_message_text_view.toggleVisibility(result is GetTicketsUseCase.Result.Empty)
        recycler_view.toggleVisibility(result is GetTicketsUseCase.Result.Success)
    }*/

    private fun setupRecyclerView(tickets: List<Ticket>) {
        val lm = GridLayoutManager(requireContext(), SPAN_COUNT)
        val ticketAdapter = TicketAdapter(tickets)
        ticketAdapter.setOnItemClickListener(this)

        recycler_view.apply {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = ticketAdapter
        }
    }

    override fun onTicketsClick(ticket: Int) {
        Toast.makeText(requireContext(), "Click on ticket $ticket", Toast.LENGTH_SHORT).show()
    }
}
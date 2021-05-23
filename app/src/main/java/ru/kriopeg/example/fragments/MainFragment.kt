package ru.kriopeg.example.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.*
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import ru.kriopeg.example.R
import ru.kriopeg.example.domain.usecases.GetSolvedQuestionsCountUseCase
import ru.kriopeg.example.viewmodels.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment(), View.OnClickListener {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // just example value
        label_ready_percent.text = getString(R.string.ready_percent, 8)

        setClickListeners()
        observeSolvedCount()
    }

    private fun observeSolvedCount() {
        viewModel.solvedCountLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is GetSolvedQuestionsCountUseCase.Result.Loading -> {
                    value_solved_questions.text = getString(R.string.loading)
                }
                is GetSolvedQuestionsCountUseCase.Result.Success -> {
                    val animation = ObjectAnimator.ofInt(progress_bar, "progress", 0, it.answersCount)
                    animation.apply {
                        duration = 1500
                        startDelay = 500
                        interpolator = DecelerateInterpolator()
                        addUpdateListener { value ->
                            if (value_solved_questions != null) {
                                value_solved_questions.text = (value.animatedValue as? Int)?.toString() ?: "0"
                            } else {
                                removeAllUpdateListeners()
                            }
                        }
                        start()
                    }
                }
            }
        })
    }

    private fun setClickListeners() {
        btn_tickets.setOnClickListener(this)
        btn_exam.setOnClickListener(this)
        btn_stats.setOnClickListener(this)
        card_adaptive.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_tickets -> findNavController().navigate(R.id.action_mainFragment_to_ticketsFragment)
            R.id.btn_exam -> Toast.makeText(requireContext(), "//TODO exam", Toast.LENGTH_SHORT).show()
            R.id.card_adaptive -> Toast.makeText(requireContext(), "//TODO adaptive", Toast.LENGTH_SHORT).show()
            R.id.btn_stats -> Toast.makeText(requireContext(), "//TODO statistics", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionSettings ->  {
                Toast.makeText(requireContext(), "//TODO settings", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

}
package ru.kriopeg.example.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_ticket.view.*
import ru.kriopeg.example.R
import ru.kriopeg.example.domain.models.Ticket

class TicketAdapter(var tickets: List<Ticket>) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    class TicketViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val titleTextView = view.number_text_view
        private val rightAnswersTextView = view.right_answers_text_view
        private val progressBar = view.progress_bar

        fun bind(number: Int, questionsCount: Int, rightCount: Int, itemClickListener: OnItemClickListener?) {
            titleTextView.text = view.context.getString(R.string.ticket_title, number)
            rightAnswersTextView.text = view.context.getString(R.string.answered_questions_count, rightCount, questionsCount)
            progressBar.max = questionsCount
            progressBar.progress = rightCount

            if (rightCount == questionsCount) {
                progressBar.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.colorPositive))
            } else {
                progressBar.progressTintList = null
            }

            view.setOnClickListener{
                itemClickListener?.onTicketsClick(number)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = tickets[position]
        holder.bind(number = ticket.number,
            questionsCount = ticket.questionsCount,
            rightCount = ticket.rightAnswersCount,
            itemClickListener = itemClickListener)
    }

    private fun getItem(position: Int) : Ticket {
        return tickets[position]
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onTicketsClick(ticket: Int)
    }

}
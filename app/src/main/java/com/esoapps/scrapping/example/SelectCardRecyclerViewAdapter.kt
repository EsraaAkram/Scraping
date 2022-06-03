package com.esoapps.scrapping.example

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.io.File

class SelectCardRecyclerViewAdapter(selectedCardsList: List<SelectedCard>,

                                    context: Context,
                                    onSelectedCardClickListener: OnSelectedCardClickListener
) : RecyclerView.Adapter<SelectCardRecyclerViewAdapter.SelectCardViewHolder>()
{

    private val selectedCardsList: List<SelectedCard>


    private val context: Context
    private val onSelectedCardClickListener: OnSelectedCardClickListener


    init {
        this.selectedCardsList = selectedCardsList

        this.context = context
        this.onSelectedCardClickListener = onSelectedCardClickListener


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectCardRecyclerViewAdapter.SelectCardViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.select_card_row_item, parent, false)


        return SelectCardViewHolder(view, onSelectedCardClickListener)
    }

    override fun onBindViewHolder(holder: SelectCardRecyclerViewAdapter.SelectCardViewHolder, position: Int) {


        val selectedCard: SelectedCard = selectedCardsList[position]



        val storedCardImgUri: String = selectedCard.imgUrl


        if (position==0){
            holder.line.visibility=View.INVISIBLE
        }

            Picasso.get()
                .load(storedCardImgUri)
                .error(R.drawable.ic_launcher_background)
                //.fit()
                //.resize(50,50)
                //.centerCrop() // Image scaling type
                //.onlyScaleDown()
                .into(holder.imgSelectCardItem)

        holder.titleTv.text=selectedCard.title
        //holder.descTv.text=selectedCard.desc

//        if(selectedCard.desc!="nothing"){
//            holder.descTv.visibility=View.VISIBLE
//            holder.descTv.text=selectedCard.desc
//
//        }


        }




    override fun getItemCount(): Int {
        return selectedCardsList.size
    }


    inner class SelectCardViewHolder(
        itemView: View,
        private val onSelectedCardClickListener: OnSelectedCardClickListener)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {



        var line: View = itemView.findViewById(R.id.line)

        var titleTv: TextView = itemView.findViewById(R.id.titleTv)
        var imgSelectCardItem: ImageView = itemView.findViewById(R.id.img)
        var descTv: TextView = itemView.findViewById(R.id.descTv)



        init {

            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View) {

            //var adapterPosition = adapterPosition

            var adapterPosition = bindingAdapterPosition
            if (adapterPosition == RecyclerView.NO_POSITION) {

                return
            }  else {


                onSelectedCardClickListener.onSelectedCardTappedListener(
                    adapterPosition
                )



            }
        }


    }




    interface OnSelectedCardClickListener {
        fun onSelectedCardTappedListener(
            position: Int)
    }




}
package com.esoapps.scrapping.example

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class DetailsAct : AppCompatActivity() {

    private var titleTvDetailsAct:TextView?=null
    private var imgDetailsAct:ImageView?=null
    private var descTvDetailsAct:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


        titleTvDetailsAct=findViewById(R.id.titleTvDetailsAct)
        imgDetailsAct=findViewById(R.id.imgDetailsAct)
        descTvDetailsAct=findViewById(R.id.descTvDetailsAct)

       var extras = intent.extras
        if (extras!=null){
            var selectedCard:SelectedCard= extras.getSerializable("articleDetails") as SelectedCard
            //var selectedCard1:SelectedCard= intent.getSerializableExtra("articleDetails") as SelectedCard

            Log.d("articleDetails",selectedCard.imgUrl)
            //Log.d("articleDetails",selectedCard1.imgUrl)





            Picasso.get()

                .load(selectedCard.imgUrl)
                .error(R.drawable.ic_launcher_background)
                //.placeholder(R.drawable.select_card_a)
                .error(R.drawable.ic_launcher_background)
                //.fit()
                //.resize(50,50)
                //.centerCrop() // Image scaling type
                //.onlyScaleDown()
                .into(imgDetailsAct)


            titleTvDetailsAct!!.text=selectedCard.title

            val doc: Document = Jsoup
                .connect(selectedCard.articleRef)
                .get()

            val elements: Elements = doc.getElementsByClass("article-body")


            val sb = StringBuilder()

            for (element in elements){

                var p=element.select("p")

                Log.d("articleDetails", p.toString())


                //TODO:SMALL TEST FOR YOU REMOVE READ MORE FROM THE ARTICLE
                sb.append(p.text()).append('\n')


            }


            Log.d("articleDetailsSb",sb.toString())


            descTvDetailsAct!!.text=sb.toString()





        }





    }
}
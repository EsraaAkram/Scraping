package com.esoapps.scrapping.example

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.net.URL


class MainActivity : AppCompatActivity(), SelectCardRecyclerViewAdapter.OnSelectedCardClickListener{

    private var selectedCardsList:ArrayList<SelectedCard>?=null
    private var rv: RecyclerView?=null
    private var selectCardRecyclerViewAdapter: SelectCardRecyclerViewAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


        selectedCardsList= ArrayList()

        rv=findViewById(R.id.rv)
        rv!!.layoutManager = LinearLayoutManager(this)



        val doc: Document = Jsoup
            .connect("https://www.btolat.com/league/1005/champleague")
            .get()



        val elements: Elements = doc.getElementsByClass("eNews").select("a")//.not(".pad_10").not(".pad_20")
        //Log.d("eNews",els.text())

        for (element in elements){
            Log.d("eNews",element.text())
            var imgTag=element.select("img")
            //var imgUrl=imgTag.attr("src")
            var imgUrl=imgTag.attr("data-original")
            var title=imgTag.attr("alt")
            //Log.d("eNews",element.toString())
            //IMG URL EXAMPLE:https://img.btolat.com/2022/5/31/news/284719/large.jpg//medium//small
            //ARTICLE URL EXAMPLE:https://www.btolat.com/news/284719
            Log.d("eNews",element.attr("href"))//:/news/284728
            Log.d("eNews",imgUrl)//https://img.btolat.com/2022/5/31/news/284719/large.jpg
            Log.d("eNews",title)

            var articleRef=element.attr("href").toString()
            articleRef= "https://www.btolat.com/$articleRef"
            Log.d("eNews",articleRef)

            var selectedCard:SelectedCard=SelectedCard(imgUrl,title,"nothing",articleRef)

            if (selectedCard!=null){
                selectedCardsList!!.add(selectedCard)
            }


        }





        selectCardRecyclerViewAdapter=SelectCardRecyclerViewAdapter(selectedCardsList!!,

            this@MainActivity,
            this@MainActivity)

        rv!!.adapter = selectCardRecyclerViewAdapter


        if (selectCardRecyclerViewAdapter!=null){

            selectCardRecyclerViewAdapter!!.notifyDataSetChanged()
        }



    }

    override fun onSelectedCardTappedListener(position: Int) {

        var selectedCard:SelectedCard= selectedCardsList!![position]
        var articleDetails=Intent(this@MainActivity, DetailsAct::class.java)

        articleDetails.putExtra("articleDetails",selectedCard)

        startActivity(articleDetails)

    }
}
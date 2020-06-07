package com.example.rssreader.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.Interfacses.Itemclicklistner
import com.example.rssreader.MainActivity
import com.example.rssreader.Model.Enclosure
import com.example.rssreader.Model.Example
import com.example.rssreader.Model.Feed
import com.example.rssreader.Model.Item
import com.example.rssreader.R

class FeedAdapter(private val example:Example, private  val context:Context ): RecyclerView.Adapter<FeedAdapter.Viewholder>() {

   lateinit var view :View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

         view = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false)
        return  Viewholder(view)
    }

    override fun getItemCount(): Int {

        return example.items.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.txttitle.text=example.items[position].title
        holder.txtpubdate.text=example.items[position].pubDate
        holder.txtcontect.text=example.items[position].content



        holder.itemView.setOnClickListener(View.OnClickListener {
            val webbrowser= Intent(Intent.ACTION_VIEW)
            webbrowser.setData(Uri.parse(example.items[position].link))
            context.startActivity(webbrowser)
        })




    }




     class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var  txttitle:TextView
        var  txtpubdate:TextView
        var  txtcontect:TextView
        var itemclicklistner:Itemclicklistner?=null
        init {
            txttitle=itemView.findViewById(R.id.txttitle)
            txtpubdate=itemView.findViewById(R.id.txtpubdate)
            txtcontect=itemView.findViewById(R.id.txtcontect)


        }

    }




}

package com.example.rssreader

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssreader.Adapter.FeedAdapter
import com.example.rssreader.Common.HTTPdatahandler
import com.example.rssreader.Model.Example
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val Rss_link="https://rss.nytimes.com/services/xml/rss/nyt/Science.xml"
    val Rss_to_json_API="https://api.rss2json.com/v1/api.json?rss_url="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar.title="News"
        setSupportActionBar(toolbar)

       val linearLayoutManager=LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linearLayoutManager
        loadRss()


    }
   private fun loadRss(){

        var loadRssAsync= object : AsyncTask<String,String,String>() {
            internal var mDialog =ProgressDialog(this@MainActivity)

            override fun onPreExecute() {
                mDialog.setMessage("please waite ..")
                mDialog.show()
            }
            override fun doInBackground(vararg params: String): String {
               // mDialog.show()
                var result:String
                val http= HTTPdatahandler()
                result=http.getHTTPdatahandler(params[0])
               // if (result!="")mDialog.dismiss()
                return result
            }

            override fun onPostExecute(result: String?) {
                //super.onPostExecute(result)
                mDialog.dismiss()

                var example:Example
                example=Gson().fromJson<Example>(result,Example::class.java!!)
                val adapter=FeedAdapter(example,baseContext)
                recyclerView.adapter=adapter
                adapter.notifyDataSetChanged()

            }



        }



        val get_url_data=StringBuilder(Rss_to_json_API)
        get_url_data.append(Rss_link)
        loadRssAsync.execute(get_url_data.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
      //  return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       // return super.onOptionsItemSelected(item)
        if(item.itemId==R.id.refesh)
            loadRss()
        return true
    }





















}

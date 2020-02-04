package com.puldroid.listview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val movies = listOf(
        "Iron Man",
        "Captain Marvel",
        "Thor",
        "Captain America",
        "Black Panther",
        "Doctor Strange"
    )
    val year = listOf("2008", "2019", "2010", "2011", "2018", "2016")
    val actor = listOf("RDJ", "Brie", "Chris", "Evans", "Chadwick", "Benedict")
    val images = listOf(
        R.drawable.ironman,
        R.drawable.marvel,
        R.drawable.thor,
        R.drawable.captain,
        R.drawable.panther,
        R.drawable.strange
    )

    val list = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..10000000) {
            val random = Random().nextInt(6)
            val movie = Movie(
                movies[random],
                actor[random],
                year[random],
                images[random]
            )
            list.add(movie)
        }

        listView.adapter = MovieAdapter(list)
//            ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, movies)
    }
}

class MovieAdapter(val movies:ArrayList<Movie>) :  BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView?:LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie,parent,false
        )
        view.textView.text = movies[position].name + "(${movies[position].year})"
        view.textView2.text = movies[position].actorName
        view.imageView.setImageResource(movies[position].image)

        return view
   }

    override fun getItem(position: Int): Movie {
                return movies[position]
    }

    override fun getItemId(position: Int): Long {
//        return movies[position].id
        return position.toLong()
    }

    override fun getCount(): Int {
        return movies.size
    }

}

data class Movie(
    val name: String,
    val actorName: String,
    val year: String,
    val image: Int
)
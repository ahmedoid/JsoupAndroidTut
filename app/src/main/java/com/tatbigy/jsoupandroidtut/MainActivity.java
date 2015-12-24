package com.tatbigy.jsoupandroidtut;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tatbigy.jsoupandroidtut.adapter.RVNewsAdapter;
import com.tatbigy.jsoupandroidtut.model.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<News> news_list = new ArrayList<>();
    private android.widget.TextView text;
    private RecyclerView rv;
    RVNewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.text = (TextView) findViewById(R.id.text);
        this.rv = (RecyclerView) findViewById(R.id.rv);
        adapter = new RVNewsAdapter(this, news_list);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        new AsyncTask<String, Integer, Document>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(MainActivity.this, "Start Loading Data", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Document doInBackground(String... params) {
                Document doc = null;
                try {
                    doc = Jsoup.connect("http://www.unlimit-tech.com/blog/").get();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                return doc;

            }

            @Override
            protected void onPostExecute(Document document) {
                super.onPostExecute(document);
                Toast.makeText(MainActivity.this, "finish Loading Data", Toast.LENGTH_SHORT).show();
                Elements news = document.select("div#main");
                Elements title = news.select("article");
                Log.d("MainActivity", "This is news = " + title);

                for (Element el : title) {
                    News news_object = new News();
                    news_object.setTitle(el.select("h2").text());
                    news_object.setDate(el.select("time").text());
                    news_object.setDesc(el.select("div.cb-excerpt").select("p").text());
                    news_object.setImage(el.select("img").attr("src"));
                    news_object.setNp_views(el.select("div.cb-post-views.cb-byline-element").text());
                    news_object.setAuther(el.select("div.cb-author.cb-byline-element").text());
                    news_list.add(news_object);
                    adapter.notifyDataSetChanged();
//                    text.append("\n " + " " + el.select("h2").text());
//                    text.append("\n  " + el.select("img").attr("src"));
//                    text.append("\n " + el.select("time").text());
//                    text.append("\n " + el.select("div.cb-author.cb-byline-element").text());
//                    text.append("\n " + el.select("div.cb-post-views.cb-byline-element").text());
//                    text.append("\n " + el.select("div.cb-excerpt").select("p").text());
                }

            }
        }.execute();


    }
}

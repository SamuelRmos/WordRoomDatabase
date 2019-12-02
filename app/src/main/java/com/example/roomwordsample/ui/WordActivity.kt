package com.example.roomwordsample.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomwordsample.R
import com.example.roomwordsample.recyclerview.WordListAdapter
import com.example.roomwordsample.room.Word
import com.example.roomwordsample.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_word.*

private lateinit var wordViewModel: WordViewModel
private const val newWordActivityRequestCode = 1

class WordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        val adapter = WordListAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.addItemDecoration(DividerItemDecoration(
            this,DividerItemDecoration.VERTICAL
        ))

        recyclerview.layoutManager = LinearLayoutManager(this)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })

        fab.setOnClickListener{
            val intent = Intent(this, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                val word = Word(it)
                wordViewModel.insert(word)
            }
        }else{
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}

package com.example.quoteapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var tvQuote: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var btnPrev: Button
    private lateinit var btnNext: Button
    private lateinit var btnCopy: Button

    private val quotes = ArrayList<String>()
    private val authors = ArrayList<String>()
    private var currentIndex = 0
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvQuote = findViewById(R.id.tvQuote)
        tvAuthor = findViewById(R.id.tvAuthor)
        btnPrev = findViewById(R.id.btnPrev)
        btnNext = findViewById(R.id.btnNext)
        btnCopy = findViewById(R.id.btnCopy)

        // 🔟 Quotes
        quotes.add("Believe in yourself.")
        authors.add("Anonymous")

        quotes.add("Success is not final, failure is not fatal.")
        authors.add("Winston Churchill")

        quotes.add("Dream big and dare to fail.")
        authors.add("Norman Vaughan")

        quotes.add("Hard work beats talent when talent doesn’t work hard.")
        authors.add("Tim Notke")

        quotes.add("Don’t watch the clock; do what it does. Keep going.")
        authors.add("Sam Levenson")

        quotes.add("The future depends on what you do today.")
        authors.add("Mahatma Gandhi")

        quotes.add("Opportunities don’t happen. You create them.")
        authors.add("Chris Grosser")

        quotes.add("It always seems impossible until it’s done.")
        authors.add("Nelson Mandela")

        quotes.add("Push yourself, because no one else is going to do it for you.")
        authors.add("Anonymous")

        quotes.add("Success doesn’t just find you. You have to go out and get it.")
        authors.add("Anonymous")

        // Show random quote at start
        currentIndex = random.nextInt(quotes.size)
        showQuote(currentIndex)

        btnNext.setOnClickListener {
            currentIndex = (currentIndex + 1) % quotes.size
            showQuote(currentIndex)
        }

        btnPrev.setOnClickListener {
            currentIndex = if (currentIndex - 1 < 0) quotes.size - 1 else currentIndex - 1
            showQuote(currentIndex)
        }

        btnCopy.setOnClickListener {
            copyQuote()
        }
    }

    private fun showQuote(index: Int) {
        tvQuote.text = "\"${quotes[index]}\""
        tvAuthor.text = "- ${authors[index]}"
    }

    private fun copyQuote() {
        val text = "${quotes[currentIndex]} - ${authors[currentIndex]}"
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Quote", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Quote copied!", Toast.LENGTH_SHORT).show()
    }
}

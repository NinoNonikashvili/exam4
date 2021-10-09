package com.example.exam4

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.RippleDrawable
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exam4.databinding.FragmentGamePageBinding
import com.example.exam4.databinding.FragmentStartPageBinding

class gamePage : Fragment() {
    private var counter: Int = 0
    private var image: Int = 0

    private val listX: MutableList<Int> = mutableListOf()
    private val listO: MutableList<Int> = mutableListOf()
    private lateinit var binding: FragmentGamePageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val row = arguments?.getString("row")


        val list = if (row?.toInt() == 3) List(9) { R.mipmap.ic_launcher }
        else if (row!!.toInt() == 4) List(16) { R.mipmap.ic_launcher }
        else List(25) { R.mipmap.ic_launcher }

        binding = FragmentGamePageBinding.inflate(inflater, container, false)

        createBoard(row.toInt())
        setListenerOnitem(row.toInt())
//        binding.recyclerView.adapter = adapter(list)
//        binding.recyclerView.layoutManager = GridLayoutManager(activity, row.toInt())

        return binding.root
    }

    fun createBoard(row: Int) {

        var id = 1
        binding.myGrid.rowCount = row
        binding.myGrid.columnCount = row


        for (i in 0..row * row - 1) {
            var btn = ImageButton(activity)
            var id = 0
            btn.id = id
            btn.setImageResource(R.drawable.ic_noun_square_88238)
            binding.myGrid.addView(btn)
            id++

        }

    }

    fun setListenerOnitem(row: Int) {
        for (i in 0..row * row - 1) {
            binding.myGrid.getChildAt(i).setOnClickListener { gameOn(i, row) }
        }
    }

    fun gameOn(i: Int, row: Int) {

        if (counter % 2 == 0) {
            image = R.drawable.ic_x_svgrepo_com
            listX.add(i)

        } else {
            image = R.drawable.ic_circle_svgrepo_com
            listO.add(i)
        }
        val clickedBtn: ImageButton = binding.myGrid.getChildAt(i) as ImageButton
        clickedBtn.setImageResource(image)
        clickedBtn.setOnClickListener(null)
        checkPattern(row.toInt())
        if (counter == row * row - 1) end("It's draw", row)

        counter++
    }

    fun end(text: String, row:Int) {
        Toast.makeText(activity, "Game Over $text", Toast.LENGTH_SHORT).show()
        for(i in 0.. row*row-1)
        binding.myGrid.getChildAt(i).setOnClickListener(null)
    }

    fun checkPattern(row: Int) {


        for (i in 0 until row - 1) {
            var winningRowPattern = mutableListOf<Int>()
            var winningColPattern = mutableListOf<Int>()

            for (j in i * row until (row * (i + 1)))
                winningRowPattern.add(j)
            for (k in i until row * row step row)
                winningColPattern.add(k)


            if (listX.containsAll(winningRowPattern) ||  //check rows
                listX.containsAll(winningColPattern) ||  // check columns
                listX.containsAll(listOf(0, 4, 7)) ||
                listX.containsAll(listOf(2, 4, 6)) ||
                listX.containsAll(listOf(0, 7, 14)) ||
                listX.containsAll(listOf(4, 7, 10))
            ) {
                end("player 1 wins", row)
                return
            } else if (listO.containsAll(winningRowPattern) ||
                listO.containsAll(winningColPattern) ||
                listO.containsAll(listOf(0, 4, 7)) ||
                listO.containsAll(listOf(2, 4, 6)) ||
                listO.containsAll(listOf(0, 7, 14)) ||
                listO.containsAll(listOf(4, 7, 10))
            ) {
                end("player 2 wins", row)
                return
            }

        }

    }
}
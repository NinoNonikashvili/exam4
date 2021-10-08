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
    private var color: Int = 0
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
            Log.d("firstbtn", "$id")
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
        if (counter == row * row - 1) end(row)


        Log.d("counter", "$counter")

        counter++
    }

    fun end(row:Int) {
        checkPattern(row.toInt())
        Toast.makeText(activity, "Game Over", Toast.LENGTH_SHORT).show()
    }
    fun checkPattern(row:Int) {


            for (i in 0 until row-1) {
                if (listX == listOf(i until row-1) ||
                    listX == listOf(i until row-1 step row-1) ||
                    listX == listOf(0, 4, 7) ||
                    listX == listOf(2, 4, 6)||
                    listX == listOf(0, 7, 14)||
                    listX == listOf(4, 7, 10)
                ) Toast.makeText(activity, "player 1 wins", Toast.LENGTH_SHORT).show()
                return
            }
            for (i in 0..2) {
                if (listO == listOf(i until row-1) ||
                    listO == listOf(i until row-1 step row-1) ||
                    listO == listOf(0, 4, 7) ||
                    listO == listOf(2, 4, 6)||
                    listO == listOf(0, 7, 14)||
                    listO == listOf(4, 7, 10)
                ) Toast.makeText(activity, "player 2 wins", Toast.LENGTH_SHORT).show()
                return
            }
        Toast.makeText(activity, "nobody wins", Toast.LENGTH_SHORT).show()




    }

}
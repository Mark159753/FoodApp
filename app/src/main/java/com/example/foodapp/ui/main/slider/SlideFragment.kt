package com.example.foodapp.ui.main.slider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.foodapp.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


private const val TITLE_PARAM1 = "param1"
private const val IMG_PARAM2 = "param2"


class SlideFragment : Fragment() {
    private var title: String? = null
    private var imgUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(TITLE_PARAM1)
            imgUrl = it.getString(IMG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.random_meal_slide, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val img = view.findViewById<ImageView>(R.id.meal_img_slider)
        val text = view.findViewById<TextView>(R.id.meal_text_slider)
        Picasso.get()
            .load(imgUrl)
            .into(img)
        text.text = title
    }


    companion object {
        fun newInstance(title: String, imagUrl: String) =
            SlideFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE_PARAM1, title)
                    putString(IMG_PARAM2, imagUrl)
                }
            }
    }
}

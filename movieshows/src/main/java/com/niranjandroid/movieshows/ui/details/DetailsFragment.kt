package com.niranjandroid.movieshows.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.app.App
import com.niranjandroid.movieshows.ui.base.BaseAbstractFragment
import com.niranjandroid.movieshows.ui.listing.ListingContract
import javax.inject.Inject

/**
 * Created by Niranjan P on 10/21/2017.
 */

class DetailsFragment : BaseAbstractFragment(){

    var imgMovie: ImageView? = null
    var tvTitle: TextView? = null
    var tvReleaseDate: TextView? = null
    var tvRating: TextView? = null

    @Inject
    override lateinit var presenter: ListingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_details, container, false)
    }

    override fun init() {
        initViews();
    }

    private fun initViews() {
        imgMovie = activity?.findViewById<View>(R.id.img_movie) as ImageView
        tvTitle = activity?.findViewById<View>(R.id.tv_title) as TextView
        tvReleaseDate = activity?.findViewById<View>(R.id.tv_release_date) as TextView
        tvRating = activity?.findViewById<View>(R.id.tv_rating) as TextView
    }


    override fun onDestroy() {
        super.onDestroy()
        (activity.application as App).releaseMovieListingComponent()
    }

    companion object {
        fun newInstance(): DetailsFragment {
            return DetailsFragment()
        }
    }
}
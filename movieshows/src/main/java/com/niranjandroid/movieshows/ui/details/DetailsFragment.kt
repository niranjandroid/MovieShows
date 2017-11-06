package com.niranjandroid.movieshows.ui.details

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.niranjandroid.movieshows.Constants
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.app.App
import com.niranjandroid.movieshows.data.model.CastModel
import com.niranjandroid.movieshows.data.model.Crew
import com.niranjandroid.movieshows.data.model.MovieModel
import com.niranjandroid.movieshows.data.model.Video
import com.niranjandroid.movieshows.ui.base.BaseAbstractFragment
import com.niranjandroid.movieshows.ui.base.PostersHorizontalAdapter
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Niranjan P on 10/21/2017.
 */

class DetailsFragment : BaseAbstractFragment(), DetailsContract.View {

    private var movie: MovieModel? = null

    @Inject
    override lateinit var presenter: DetailsContract.Presenter

    @Inject
    @field:Named("cast")
    @JvmField
    var castAdapter: ImageHorizontalAdapter? = null

    @Inject
    @JvmField
    @field:Named("crew")
    var crewAdapter: ImageHorizontalAdapter? = null

    @Inject
    @JvmField
    @field:Named("trailers")
    var trailersAdapter: ImageHorizontalAdapter? = null

    private var images: MutableList<String> = ArrayList()
    private var castList: MutableList<CastModel> = ArrayList()
    private var crewList: MutableList<Crew> = ArrayList()
    private var trailers: MutableList<Video> = ArrayList()

    @Inject
    @JvmField
    var posterHorizontalAdapter: PostersHorizontalAdapter? = null


    companion object {
        fun newInstance(movie: String?): DetailsFragment {
            val detailsFragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(Constants.MOVIE_OBJ, movie)
            detailsFragment.arguments = bundle
            return detailsFragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_details, container, false)
    }

    override fun init() {
        (activity.application as App).getDetailsComponent()?.inject(this)
        presenter.attachView(this)
        movie = Gson().fromJson(arguments?.getString(Constants.MOVIE_OBJ), MovieModel::class.java)
        updateData();
        movie?.backdropPath?.let { images.add(movie?.backdropPath!!) }

        var isReverse = false

        posterHorizontalAdapter?.init(images)
        listPosters.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, isReverse)
        listPosters.adapter = posterHorizontalAdapter

        castAdapter?.initWithCasts(castList);
        listCast.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, isReverse)
        listCast.adapter = castAdapter

        crewAdapter?.initWithCrew(crewList)
        listCrew.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, isReverse)
        listCrew.adapter = crewAdapter

        trailersAdapter?.initWithTrailers(trailers)
        listTrailers.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, isReverse)
        listTrailers.adapter = trailersAdapter

        presenter.getMovieDetails(movie?.id)
    }

    override fun onFetchingMovieDetails(movieModel: MovieModel) {
        movie = movieModel
        updateData()
    }

    private fun updateData() {
        var title = movie?.title
        movie?.tagline?.let { movie?.tagline?.isNotEmpty()?.let { title = "$title (${movie?.tagline})"  }}
        tvTitle?.text = title?.replace("()", "")
        tvReleaseDate?.text = movie?.releaseDate
        tvRating?.text = String.format(activity.getString(R.string.info_rating),
                movie?.voteAverage, movie?.voteCount)

        tvGenres.text = presenter.getGenres(movie?.genres)
        tvSummary.text = movie?.overview
        movie?.images?.let { updatePosters(presenter.getImagesFromImageModel(movie?.images!!)) }
        movie?.casts?.castList?.let { updateCast(movie?.casts?.castList!!) }
        movie?.casts?.crewList?.let { updateCrew(movie?.casts?.crewList!!) }
        movie?.videos?.videos?.let { updateTrailers(movie?.videos?.videos!!) }
    }

    private fun updateTrailers(trailers : MutableList<Video>) {
        tvTrailers.visibility = View.VISIBLE
        listTrailers.visibility = View.VISIBLE
        this.trailers.clear()
        this.trailers.addAll(trailers)
        trailersAdapter?.notifyDataSetChanged()
    }

    private fun updateCrew(crewList: MutableList<Crew>) {
        tvCrew.visibility = View.VISIBLE
        listCrew.visibility = View.VISIBLE
        this.crewList.clear()
        this.crewList.addAll(crewList)
        crewAdapter?.notifyDataSetChanged()
    }

    private fun updateCast(castList: MutableList<CastModel>) {
        tvCast.visibility = View.VISIBLE
        listCast.visibility = View.VISIBLE
        this.castList.clear()
        this.castList.addAll(castList)
        castAdapter?.notifyDataSetChanged()
    }

    override fun updatePosters(images: MutableList<String>) {
        this.images.clear()
        this.images.addAll(images)
        posterHorizontalAdapter?.notifyDataSetChanged()
    }


    override fun onDestroy() {
        super.onDestroy()
        (activity.application as App).releaseDetailsComponent()
    }

}
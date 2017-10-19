
package com.niranjandroid.movieshows.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MovieListModel {

    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<MovieModel> mMovies;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    @SerializedName("dates")
    private Dates mDates;

    public Dates getDates() {
        return mDates;
    }

    public void setDates(Dates dates) {
        mDates = dates;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<MovieModel> getResults() {
        return mMovies;
    }

    public void setResults(List<MovieModel> movies) {
        mMovies = movies;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

    public static class Dates {

        @SerializedName("maximum")
        private String mMaximum;
        @SerializedName("minimum")
        private String mMinimum;

        public String getMaximum() {
            return mMaximum;
        }

        public void setMaximum(String maximum) {
            mMaximum = maximum;
        }

        public String getMinimum() {
            return mMinimum;
        }

        public void setMinimum(String minimum) {
            mMinimum = minimum;
        }

    }
}

package com.bhupen.moviedisplaylist.app.ui.discover

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.bhupen.moviedisplaylist.R
import com.bhupen.moviedisplaylist.common.base.BasePagingDataAdapter
import com.bhupen.moviedisplaylist.common.utils.DateTimeUtil
import com.bhupen.moviedisplaylist.common.utils.loadImage
import com.bhupen.moviedisplaylist.data.local.entity.MovieEntity
import kotlinx.android.synthetic.main.item_grid_list.view.*
import javax.inject.Inject


class MoviePagingDataAdapter @Inject constructor() :
    BasePagingDataAdapter<MovieEntity>(
        DiffCallback
    ) {

    var onMovieClickedCallback: ((movieId: Long) -> Unit)? = null

    override fun getLayoutId(): Int {
        return R.layout.item_grid_list
    }

    override fun bindView(): (item: MovieEntity, itemView: View, position: Int) -> Unit {
        return { item, itemView, _ ->
            itemView.apply {
                title.text = item.title
                date.text = DateTimeUtil.getYearMonthFromDateString(item.releaseDate)
                imageView.loadImage(item.posterLink)
                setOnClickListener {
                    onMovieClickedCallback?.invoke(item.id)
                }
            }
        }

    }

    object DiffCallback : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(
            oldItem: MovieEntity,
            newItem: MovieEntity
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MovieEntity,
            newItem: MovieEntity
        ): Boolean =
            oldItem == newItem
    }
}
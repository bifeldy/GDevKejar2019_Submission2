package id.ac.umn.made_basiliusbias_submission2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import id.ac.umn.made_basiliusbias_submission2.R;
import id.ac.umn.made_basiliusbias_submission2.activities.DetailsActivity;
import id.ac.umn.made_basiliusbias_submission2.pojos.Film;

import java.text.DecimalFormat;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Film> films;
    private Context recyclerContext;
    private int rowLayout;

    public MovieAdapter(Context recyclerContext, int rowLayout, List<Film> films) {
        this.recyclerContext = recyclerContext;
        this.rowLayout = rowLayout;
        this.films = films;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // Inflate the layout for recycler content
        LayoutInflater inflater = LayoutInflater.from(this.recyclerContext);
        View view = inflater.inflate(rowLayout, viewGroup, false);

        return new MovieAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {

        // Get Movie
        final Film film = films.get(i);

        // Load Image
        Glide.with(recyclerContext)
            .load(film.getPhoto())
            .transition(DrawableTransitionOptions.withCrossFade(250)) // Transition Effect Load Image
            .apply(new RequestOptions().override(175, 247))
            .into(movieViewHolder.movies_image);

        // Set Title
        movieViewHolder.movies_title.setText(film.getTitle());

        // Set Score
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        movieViewHolder.movies_score.setText(decimalFormat.format(film.getScore()));

        // Set Film Type
        movieViewHolder.movies_type.setText(film.getType());

        // Click Listener
        movieViewHolder.itemView.setOnClickListener(v -> {

            // Open Detail Activity And Passing Data
            Intent intent = new Intent(recyclerContext, DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_DETAIL, film);
            recyclerContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return (films != null) ? films.size() : 0; }

    // Inner Class
    class MovieViewHolder extends RecyclerView.ViewHolder {

        // UI Object
        private ImageView movies_image;
        private TextView movies_title, movies_score, movies_type;

        private MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find UI Object
            movies_image = itemView.findViewById(R.id.movies_image);
            movies_title = itemView.findViewById(R.id.movies_title);
            movies_score = itemView.findViewById(R.id.movies_score);
            movies_type = itemView.findViewById(R.id.movies_type);

            // Click Listener Will Be Override By onBindViewHolder
            itemView.setOnClickListener(v -> {
                /* int position = getAdapterPosition(); */
            });
        }
    }
}

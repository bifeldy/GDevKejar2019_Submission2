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

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {

    private List<Film> films;
    private Context recyclerContext;
    private int rowLayout;

    public TVShowAdapter(Context recyclerContext, int rowLayout, List<Film> films) {
        this.recyclerContext = recyclerContext;
        this.rowLayout = rowLayout;
        this.films = films;
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // Inflate the layout for recycler content
        LayoutInflater inflater = LayoutInflater.from(this.recyclerContext);
        View view = inflater.inflate(rowLayout, viewGroup, false);

        return new TVShowAdapter.TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder tvShowViewHolder, int i) {

        // Get Movie
        final Film film = films.get(i);

        // Load Image
        Glide.with(recyclerContext)
                .load(film.getPhoto())
                .transition(DrawableTransitionOptions.withCrossFade(250)) // Transition Effect Load Image
                .apply(new RequestOptions().override(175, 247))
                .into(tvShowViewHolder.tv_show_image);

        // Set Title
        tvShowViewHolder.tv_show_title.setText(film.getTitle());

        // Set Description
        tvShowViewHolder.tv_show_description.setText(film.getDescription());

        // Set Score
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        tvShowViewHolder.tv_show_score.setText(decimalFormat.format(film.getScore()));

        // Set Film Type
        tvShowViewHolder.tv_show_type.setText(film.getType());

        // Click Listener
        tvShowViewHolder.itemView.setOnClickListener(v -> {

            // Open Detail Activity And Passing Data
            Intent intent = new Intent(recyclerContext, DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_DETAIL, film);
            recyclerContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return (films != null) ? films.size() : 0; }

    // Inner Class
    class TVShowViewHolder extends RecyclerView.ViewHolder {

        // UI Object
        private ImageView tv_show_image;
        private TextView tv_show_title, tv_show_description, tv_show_score, tv_show_type;

        private TVShowViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find UI Object
            tv_show_image = itemView.findViewById(R.id.tv_show_image);
            tv_show_title = itemView.findViewById(R.id.tv_show_title);
            tv_show_description = itemView.findViewById(R.id.tv_show_description);
            tv_show_score = itemView.findViewById(R.id.tv_show_score);
            tv_show_type = itemView.findViewById(R.id.tv_show_type);

            // Click Listener Will Be Override By onBindViewHolder
            itemView.setOnClickListener(v -> {
                /* int position = getAdapterPosition(); */
            });
        }
    }
}

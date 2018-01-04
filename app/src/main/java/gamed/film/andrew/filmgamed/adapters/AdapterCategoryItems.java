package gamed.film.andrew.filmgamed.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gamed.film.andrew.filmgamed.R;
import gamed.film.andrew.filmgamed.activities.CategoryActivity;
import gamed.film.andrew.filmgamed.models.ModelCategoryDetails;
import gamed.film.andrew.filmgamed.models.ModelItem;
import gamed.film.andrew.filmgamed.singleton.SingletonData;

public class AdapterCategoryItems extends RecyclerView.Adapter<AdapterCategoryItems.ViewHolder>
{


    public List<ModelItem> data;
    private Activity mContext;


    public AdapterCategoryItems(List<ModelItem> data, Activity mContext)
    {
        this.data = data;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {

        ModelItem modelItem = data.get(position);
        holder.tvRvItemItemsToShow.setText(modelItem.getShowName());
        if (!modelItem.getMovie().equals("e"))
            holder.tvRvItemItemsMovie.setText(modelItem.getMovie());

        Picasso.with(mContext)
                .load(data.get(position).getImageUri())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgRvItemItems);

        if (modelItem.getSelected())
            holder.RvItemItemsChosen.setVisibility(View.VISIBLE);

    }


    @Override
    public int getItemCount()
    {
        return data.size();
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView tvRvItemItemsToShow, tvRvItemItemsMovie;
        ImageView RvItemItemsChosen, imgRvItemItems;

        ViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            tvRvItemItemsToShow = itemView.findViewById(R.id.tvRvItemItemsToShow);
            tvRvItemItemsMovie = itemView.findViewById(R.id.tvRvItemItemsMovie);
            RvItemItemsChosen = itemView.findViewById(R.id.RvItemItemsChosen);
            imgRvItemItems = itemView.findViewById(R.id.imgRvItemItems);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v)
        {

            data.get(getAdapterPosition()).setSelected(true);
            notifyItemChanged(getAdapterPosition());
        }


    }

    // region helper functions

    public void add(String string)
    {
        insert(string, data.size());
    }

    public void remove(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void clear()
    {
        int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void insert(String string, int position)
    {
        //data.add(position, string);
        notifyItemInserted(position);
    }

    public void addAll(List<ModelItem> items)
    {
        int startIndex = data.size();
        data.addAll(items);
        notifyItemRangeInserted(startIndex, items.size());
    }
    //endregion


}

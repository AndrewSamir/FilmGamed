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
import gamed.film.andrew.filmgamed.singleton.SingletonData;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolder>
{


    public List<ModelCategoryDetails> data;
    private Activity mContext;


    public AdapterCategories(List<ModelCategoryDetails> data, Activity mContext)
    {
        this.data = data;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {

        holder.tvRvItemCategory.setText(data.get(position).getCategoryName());
        Picasso.with(mContext)
                .load(data.get(position).getCategoryImage())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgRvItemCategory);

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

        TextView tvRvItemCategory;
        ImageView imgRvItemCategory;

        ViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            imgRvItemCategory = itemView.findViewById(R.id.imgRvItemCategory);
            tvRvItemCategory = itemView.findViewById(R.id.tvRvItemCategory);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v)
        {
            SingletonData.getInstance().setCategoryName(data.get(getAdapterPosition()).getCategoryId());
            mContext.startActivity(new Intent(mContext, CategoryActivity.class));

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

    public void addAll(List<ModelCategoryDetails> items)
    {
        int startIndex = data.size();
        data.addAll(items);
        notifyItemRangeInserted(startIndex, items.size());
    }
    //endregion


}

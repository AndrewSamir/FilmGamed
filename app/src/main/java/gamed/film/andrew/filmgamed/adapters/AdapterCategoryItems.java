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
import gamed.film.andrew.filmgamed.interfaces.InterfaceAddDataToFirebase;
import gamed.film.andrew.filmgamed.models.ModelCategoryDetails;
import gamed.film.andrew.filmgamed.models.ModelItem;
import gamed.film.andrew.filmgamed.singleton.SingletonData;
import gamed.film.andrew.filmgamed.utlities.HandleAddDataToFirebase;

public class AdapterCategoryItems extends RecyclerView.Adapter<AdapterCategoryItems.ViewHolder> implements InterfaceAddDataToFirebase {


    public List<ModelItem> data;
    private Activity mContext;


    public AdapterCategoryItems(List<ModelItem> data, Activity mContext) {
        this.data = data;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        ModelItem modelItem = data.get(position);
        holder.tvRvItemItemsToShow.setText(modelItem.getShowName());
        if (!modelItem.getMovie().equals("e"))
            holder.tvRvItemItemsMovie.setText(modelItem.getMovie());
        else
            holder.tvRvItemItemsMovie.setVisibility(View.GONE);

        Picasso.with(mContext)
                .load(data.get(position).getImageUri())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imgRvItemItems);

        if (modelItem.getSelected())
            holder.RvItemItemsChosen.setVisibility(View.VISIBLE);
        else
            holder.RvItemItemsChosen.setVisibility(View.GONE);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvRvItemItemsToShow, tvRvItemItemsMovie;
        ImageView RvItemItemsChosen, imgRvItemItems;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvRvItemItemsToShow = itemView.findViewById(R.id.tvRvItemItemsToShow);
            tvRvItemItemsMovie = itemView.findViewById(R.id.tvRvItemItemsMovie);
            RvItemItemsChosen = itemView.findViewById(R.id.RvItemItemsChosen);
            imgRvItemItems = itemView.findViewById(R.id.imgRvItemItems);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            setSelected(getAdapterPosition());
        }


    }


    // region helper functions
    private void setSelected(int adapterPosition) {
        HandleAddDataToFirebase.getInstance(mContext).setClickDialogListener(this);

        HandleAddDataToFirebase.getInstance(mContext).callAddSelected(mContext.getString(R.string.flag_call_add_Selected),
                SingletonData.getInstance().getCategoryName(), data.get(adapterPosition).getShowName(), adapterPosition);

    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<ModelItem> items) {
        int startIndex = data.size();
        data.addAll(items);
        notifyItemRangeInserted(startIndex, items.size());
    }

    public void setSelectedItem(String selectedItem) {

        for (ModelItem modelItem : data) {

            if (modelItem.getShowName().equals(selectedItem)) {
                selectItem(data.indexOf(modelItem));
            }
        }
    }

    public void selectItem(int position) {

        for (int i = 0; i < data.size(); i++) {

            if (i == position)
                data.get(position).setSelected(true);
            else
                data.get(i).setSelected(false);

        }
        notifyDataSetChanged();

    }
    //endregion


    //region add data to firebase listener
    @Override
    public void onDataAddedSuccess(String flag) {

    }

    @Override
    public void onDataAddedSuccess(String flag, int position) {

        if (flag.equals(mContext.getString(R.string.flag_call_add_Selected))) {

            for (int i = 0; i < data.size(); i++) {

                if (i == position)
                    data.get(position).setSelected(true);
                else
                    data.get(i).setSelected(false);

            }
            notifyDataSetChanged();
        }
    }

    @Override
    public void onDataAddedFailed(String flag) {

    }


    //endregion
}

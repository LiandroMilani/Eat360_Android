package th.co.apps360.eat360.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import th.co.apps360.eat360.ConfigURL;
import th.co.apps360.eat360.Model.MoreFoodDataModel;
import th.co.apps360.eat360.R;
import th.co.apps360.eat360.Utils;

/**
 * Created by dan on 11/13/16.
 */

public class MarkedFIconsAdapter extends RecyclerView.Adapter<MarkedFIconsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> markedIcons = new ArrayList<>();
    private ArrayList<Integer> markedPoses = new ArrayList<>();

    public MarkedFIconsAdapter(Context c, ArrayList<String> iconurls, ArrayList<Integer> iconposes) {
        mContext = c;
        this.markedIcons = iconurls;
        this.markedPoses = iconposes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.item_additional_food, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageView.setId(position);
        String selCategoryIcon = markedIcons.get(position);
        String url = "";
        if (selCategoryIcon.contains("http"))
            url = selCategoryIcon;
        else
            url = new ConfigURL(mContext).defaultURL+"/"+selCategoryIcon;
        Utils.setImageWithPicasso(mContext, url, holder.imageView);
        holder.imageView.getDrawable().setColorFilter(new LightingColorFilter(Color.BLUE, Color.WHITE));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);
                    MoreFoodDataModel.foodSelCategoryIndexList.remove(markedPoses.get(position));
//                    MoreFoodDataModel.foodSelCategoryIndexList.clear();
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return markedIcons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imageView = (ImageView) itemLayoutView.findViewById(R.id.item_marked);
        }

    }
}

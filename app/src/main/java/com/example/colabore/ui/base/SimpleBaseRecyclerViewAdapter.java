package com.example.colabore.ui.base;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colabore.R;
import com.example.colabore.ui.widget.ProgressButton;

public abstract class SimpleBaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_EMPTY = 3;
    public static final int TYPE_FOOTER = 5;
    public static final int TYPE_INVISIBLE = 4;

    private final int HEADER_SIZE = 1;
    private final int FOOTER_SIZE = 1;
    private final int PLACE_HOLDER_SIZE = 1;
    private Context mContext;

    //FLAGS
    private boolean mShowInvisiblePlaceholder = true;
    private boolean mAlwaysShowFooter = false;
    private boolean mAlwaysShowHeader = false;

    private int emptyLabel = 0;
    private String emptyLabelArgs = "";
    private int emptyIcon = 0;

    private boolean visibilityLabel = true;
    private boolean visibilityIcon = true;

    public SimpleBaseRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_INVISIBLE:
                return getInvisibleViewHolder(parent);
            case TYPE_EMPTY:
                return getEmptyViewHolder(parent);
            case TYPE_HEADER:
                return getHeaderViewHolder(parent);
            case TYPE_FOOTER:
                return getFooterViewHolder(parent);
            default:
                return getItemViewHolder(parent);
        }
    }

    @Override
    public int getItemViewType(int position) {
//        if (mShowInvisiblePlaceholder) {
//            mShowInvisiblePlaceholder = false;
//            return TYPE_INVISIBLE;
//        } else { }

        if (getDisplayableItemsCount() == 0 && isPlaceHolderPosition(position)) {
            return TYPE_EMPTY;
        } else if (hasHeader() && isPositionHeader(position)) {
            return TYPE_HEADER;
        } else if (hasFooter() && isPositionFooter(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public boolean isPlaceHolderPosition(int position) {
        return (hasHeader() && mAlwaysShowHeader && position == 1) ||
                (!hasHeader() && position == 0) ||
                (!mAlwaysShowHeader && position == 0);
    }

    @Override
    public int getItemCount() {
        if (getDisplayableItemsCount() == 0) {
            return PLACE_HOLDER_SIZE + (mAlwaysShowHeader ? HEADER_SIZE : 0) + (mAlwaysShowFooter ? FOOTER_SIZE : 0);
        } else {
            return getDisplayableItemsCount() + (hasFooter() ? FOOTER_SIZE : 0) + (hasHeader() ? HEADER_SIZE : 0);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindRecyclerViewHolder(holder, getRealPosition(position));
        if (holder instanceof PlaceHolderVH) {
            if (holder.getItemViewType() == TYPE_EMPTY) {
                ((PlaceHolderVH) holder).bind(getEmptyLabel(getContext()), getEmptyIcon(getContext()), null);
            }
        }
    }

    public abstract int getDisplayableItemsCount();

    public abstract void onBindRecyclerViewHolder(RecyclerView.ViewHolder holder, int position);

    //ITEM METHODS
    protected abstract RecyclerView.ViewHolder getItemViewHolder(ViewGroup parent);

    //FOOTER METHODS
    protected RecyclerView.ViewHolder getFooterViewHolder(ViewGroup parent) {
        return null;
    }

    public final void alwaysShowFooter(boolean isAlways) {
        mAlwaysShowFooter = isAlways;
    }

    //HEADER METHODS
    protected RecyclerView.ViewHolder getHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    public final void alwaysShowHeader(boolean isAlways) {
        mAlwaysShowHeader = isAlways;
    }

    //EMPTY METHODS
    public void setEmptyLabel(int label) {
        emptyLabel = label;
    }

    public void setEmptyLabelArgs(String args) {
        emptyLabelArgs = args;
    }

    public void setEmptyIcon(int icon) {
        emptyIcon = icon;
    }

    public void setLabelVisibility(boolean visibility) {
        visibilityLabel = visibility;
    }

    public void setIconVisibility(boolean visibility) {
        visibilityIcon = visibility;
    }

    protected String getEmptyLabel(Context context) {
        if(emptyLabel != 0) {
            return context.getString(emptyLabel, emptyLabelArgs);

        } else {
            return context.getString(R.string.placeholder_empty_label);
        }
    }

    protected Drawable getEmptyIcon(Context context) {
        if(emptyIcon != 0) {
            return ContextCompat.getDrawable(context, emptyIcon);

        } else {
            return ContextCompat.getDrawable(context, R.drawable.ic_default_empty);
        }
    }

    protected RecyclerView.ViewHolder getEmptyViewHolder(ViewGroup parent) {
        View sectionView = LayoutInflater.from(parent.getContext()).inflate(R.layout.placeholder_default_general, parent, false);
        return new PlaceHolderVH(sectionView);
    }

    private RecyclerView.ViewHolder getInvisibleViewHolder(ViewGroup parent) {
        View sectionView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_layout, parent, false);
        return new EmptyPlaceHolderVH(sectionView);
    }

    //GETTERS
    private int getRealPosition(int position) {
        return position - (hasHeader() ? HEADER_SIZE : 0);
    }

    public final boolean hasHeader() {
        try {
            return getHeaderViewHolder(null) != null;
        } catch (NullPointerException e) {
            return true;
        }
    }

    public final boolean hasFooter() {
        try {
            return getFooterViewHolder(null) != null;
        } catch (NullPointerException e) {
            return true;
        }
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }

    //VIEW HOLDERS
    public class EmptyPlaceHolderVH extends RecyclerView.ViewHolder {

        public EmptyPlaceHolderVH(View itemView) {
            super(itemView);
        }
    }

    public class PlaceHolderVH extends RecyclerView.ViewHolder {
        private TextView vLabel;
        private ImageView vIcon;
        private ProgressButton vRefreshButton;

        public PlaceHolderVH(View v) {
            super(v);
            vLabel = itemView.findViewById(R.id.item_default_label);
            vIcon = itemView.findViewById(R.id.item_default_icon);
            vRefreshButton = itemView.findViewById(R.id.item_default_button);
        }

        public void bind(String emptyMsg, Drawable drawable, View.OnClickListener refreshClickListener) {
            if (visibilityLabel) {
                vLabel.setText(emptyMsg);
            } else {
                vLabel.setVisibility(View.GONE);
            }

            if (drawable == null || !visibilityIcon) {
                vIcon.setVisibility(View.GONE);
            } else {
                Drawable tintedDrawable = drawable.mutate();
                tintedDrawable.setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
                vIcon.setImageDrawable(tintedDrawable);
            }

            if (refreshClickListener != null) {
                vRefreshButton.setVisibility(View.VISIBLE);
                vRefreshButton.setOnClickListener(refreshClickListener);
            }
        }
    }
}
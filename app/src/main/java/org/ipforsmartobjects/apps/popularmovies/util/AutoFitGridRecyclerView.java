package org.ipforsmartobjects.apps.popularmovies.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Hamid on 2/25/2017.
 * Reference : http://blog.sqisland.com/2014/12/recyclerview-autofit-grid.html
 */

public class AutoFitGridRecyclerView extends RecyclerView {
    private GridLayoutManager mGridLayoutManager;
    private int columnWidth = 0;

    public AutoFitGridRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public AutoFitGridRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutoFitGridRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            columnWidth = array.getDimensionPixelSize(0, 0);
            array.recycle();
        }

        mGridLayoutManager = new GridLayoutManager(getContext(), 1);
        setLayoutManager(mGridLayoutManager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);

        if (columnWidth > 0) {
            mGridLayoutManager.setSpanCount(Math.max(1, getMeasuredWidth() / columnWidth));
        }
    }
}
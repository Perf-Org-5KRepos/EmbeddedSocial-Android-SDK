/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package com.microsoft.embeddedsocial.ui.view;

import com.microsoft.embeddedsocial.sdk.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Extension of {@link SwipeRefreshLayout} working if {@link RecyclerView} is not a direct child of it.
 */
public class AdvancedSwipeRefreshLayout extends SwipeRefreshLayout {

    private RecyclerView innerRecyclerView;

    public AdvancedSwipeRefreshLayout(Context context) {
        super(context);
    }

    public AdvancedSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        boolean superValue = super.canChildScrollUp();
        if (superValue) {
            return true;
        } else {
            if (innerRecyclerView == null) {
                innerRecyclerView = (RecyclerView) findViewById(R.id.es_recyclerView);
            }
            return innerRecyclerView.getVisibility() == View.VISIBLE && innerRecyclerView.canScrollVertically(-1);
        }
    }
}

package com.icefire.android.ui.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewUtils {

	 /**
     * Inflate a new view hierarchy from the specified XML resource.
     * 
     * @param resource ID for an XML layout resource to load (e.g.,
     *        <code>R.layout.asset_info_section_header</code>)
     * @param context The current context.
     * @return The root View of the inflated XML file.
     */
    public static View inflateView(int resource, Context context) {

        return inflateView(resource, context, null);
    }
    
    /**
     * Inflate a new view hierarchy from the specified xml resource.
     * 
     * @param resourceID for an XML layout resource to load (e.g.,
     * @param context The current context.
     * @param parent simply an object that provides a set of LayoutParams
     *        values for root of the returned hierarchy
     * @return The root View of the inflated XML file.
     */
    public static View inflateView(int resource, Context context, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return  vi.inflate(resource, parent, false);
    }
}

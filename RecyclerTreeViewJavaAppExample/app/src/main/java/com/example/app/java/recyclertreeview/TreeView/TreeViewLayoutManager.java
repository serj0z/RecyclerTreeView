package com.example.app.java.recyclertreeview.TreeView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class TreeViewLayoutManager extends LinearLayoutManager {
	public TreeViewLayoutManager(Context context) {
		super(context);
	}

	@Override
	public boolean supportsPredictiveItemAnimations() {
		return false;
	}
}
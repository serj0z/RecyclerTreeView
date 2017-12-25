package com.example.app.java.recyclertreeview.CustomTreeView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.java.recyclertreeview.R;
import com.example.app.java.recyclertreeview.TreeView.TreeNode;
import com.example.app.java.recyclertreeview.TreeView.TreeViewAdapter;

import java.util.List;

public class CustomTreeViewAdapter extends TreeViewAdapter {
	public CustomTreeViewAdapter(Context context, List<TreeNode> treeNodes, int style) {
		super(context, treeNodes, style);
	}

	@Override
	public TreeViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.layout_node, parent, false);
		return new CustomViewHolder(view);
	}

	@Override
	public void onBindViewHolder(TreeViewHolderBase holder, int position) {
		super.onBindViewHolder(holder, position);
		CustomTreeNode node = (CustomTreeNode) getTreeNodesVisible().get(position);
		CustomViewHolder viewHolder = (CustomViewHolder) holder;
		viewHolder.textView.setText(node.Text);
	}

	public class CustomViewHolder extends TreeViewHolderBase {
		public TextView textView;

		public CustomViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.nodeText);
		}
	}
}

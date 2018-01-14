package com.example.app.java.recyclertreeview.TreeView;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.app.java.recyclertreeview.R;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeViewAdapter extends RecyclerView.Adapter<TreeViewAdapter
		.TreeViewHolderBase> {

	private Context _context;
	private List<TreeNode> _treeNodesSource;
	private int _style;
	private List<TreeNode> _treeNodesVisible;

	public TreeViewAdapter(Context context, List<TreeNode> treeNodes, int style) {
		_context = context;

		_treeNodesSource = treeNodes;
		_style = style;
		initTreeNodesVisible();
	}

	@SuppressWarnings("ResourceType")
	@Override
	public void onBindViewHolder(TreeViewHolderBase holder, int position) {

		TreeNode node = _treeNodesVisible.get(position);
		node.ViewHolder = holder;
		TypedArray paddings = getViewDefaultPaddings();

		holder.itemView.setPadding(paddings.getDimensionPixelSize(0, 0) * node.Level, paddings
						.getDimensionPixelSize(1, 0), paddings.getDimensionPixelSize(2, 0),
				paddings.getDimensionPixelSize(3, 0));

		ImageView image = holder.ToggleImageView;

		if (node.IsExpanded) {
			image.setImageResource(android.R.drawable.checkbox_on_background);
		} else {
			image.setImageResource(android.R.drawable.checkbox_off_background);
		}

		final int pos = position;

		if (node.getChildrens().size() > 0) {
			image.setVisibility(View.VISIBLE);
			image.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					TreeNode nodeItem = getTreeNodesVisible().get(pos);
					final ImageView view = (ImageView) v;
					if (nodeItem.IsExpanded) {
						collapse(nodeItem);
					} else {
						expand(nodeItem);
					}
				}
			});
		} else {
			image.setVisibility(View.INVISIBLE);
		}

		paddings.recycle();
	}

	private TypedArray getViewDefaultPaddings() {
		int[] attrs = {android.R.attr.paddingLeft, android.R.attr.paddingTop, android.R.attr.paddingRight, android.R.attr.paddingBottom};
		TypedArray ta = _context.obtainStyledAttributes(_style, attrs);
		return ta;
	}

	@Override
	public int getItemCount() {
		return _treeNodesVisible.size();
	}

	public List<TreeNode> getTreeNodesVisible() {
		return _treeNodesVisible;
	}

	private void initTreeNodesVisible() {
		_treeNodesVisible = _treeNodesSource;
	}

	public void expand(TreeNode node) {

		int position = _treeNodesVisible.indexOf(node);
		node.IsExpanded = true;

		if (node.ViewHolder != null) {
			node.ViewHolder.ToggleImageView.setImageResource(android.R.drawable.checkbox_on_background);
		}

		List<TreeNode> childNodes = node.getChildrens();
		_treeNodesVisible.addAll(position + 1, childNodes);

		notifyItemRangeInserted(position + 1, childNodes.size());
	}

	public void collapse(TreeNode node) {
		int position = _treeNodesVisible.indexOf(node);
		node.IsExpanded = false;

		if (node.ViewHolder != null) {
			node.ViewHolder.ToggleImageView.setImageResource(android.R.drawable.checkbox_off_background);
		}

		int expandedItemsCount = getExpandedCount(node);
		if (expandedItemsCount > 0) {
			List<TreeNode> expandedItems = new ArrayList<>();

			int counter = position + 1;
			for (int i = 0; i < expandedItemsCount; i++) {
				expandedItems.add(_treeNodesVisible.get(counter));
				counter++;
			}

			for (TreeNode item :
					expandedItems) {
				if (item.IsExpanded) {
					item.IsExpanded = false;
				}
			}

			_treeNodesVisible.removeAll(expandedItems);

		}
		notifyItemRangeRemoved(position + 1, expandedItemsCount);
		notifyItemRangeChanged(position, _treeNodesVisible.size());
	}

	public void expandAll() {
		for (TreeNode node :
				_treeNodesVisible) {
			if (node.getChildrens().size() > 0 && !node.IsExpanded) {
				expand(node);
				expandAll();
				break;
			}
		}
	}

	public void collapseAll() {
		for (TreeNode node :
				_treeNodesVisible) {
			if (node.getChildrens().size() > 0 && node.IsExpanded) {
				collapse(node);
				collapseAll();
				break;
			}
		}
	}

	private int getExpandedCount(TreeNode node) {
		List<TreeNode> childrens = node.getChildrens();
		int count = childrens.size();

		for (TreeNode child :
				childrens) {
			if (child.IsExpanded) {
				count += getExpandedCount(child);
			}
		}

		return count;
	}

	public abstract class TreeViewHolderBase extends RecyclerView.ViewHolder {
		public ImageView ToggleImageView;

		public TreeViewHolderBase(View itemView) {
			super(itemView);
			ToggleImageView = (ImageView) itemView.findViewById(R.id.toggleImage);
		}
	}
}


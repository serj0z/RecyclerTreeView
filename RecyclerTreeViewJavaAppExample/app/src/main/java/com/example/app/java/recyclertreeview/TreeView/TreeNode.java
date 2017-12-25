package com.example.app.java.recyclertreeview.TreeView;


import java.util.ArrayList;
import java.util.List;

public abstract class TreeNode {
	public int Level;
	public boolean IsExpanded;
	public boolean IsSelected;
	private List<TreeNode> _childrens;

	public TreeViewAdapter.TreeViewHolderBase ViewHolder;

	public TreeNode() {
		_childrens = new ArrayList<>();
	}

	public void addChild(TreeNode child) {
		_childrens.add(child);
	}

	public void addChildren(List<TreeNode> childrens) {
		_childrens.addAll(childrens);
	}

	public List<TreeNode> getChildrens() {
		return _childrens;
	}
}
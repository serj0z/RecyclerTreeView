package com.example.app.java.recyclertreeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.app.java.recyclertreeview.CustomTreeView.CustomTreeNode;
import com.example.app.java.recyclertreeview.CustomTreeView.CustomTreeViewAdapter;
import com.example.app.java.recyclertreeview.TreeView.TreeNode;
import com.example.app.java.recyclertreeview.TreeView.TreeViewLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new TreeViewLayoutManager(this));
		recyclerView.setNestedScrollingEnabled(false);

		CustomTreeViewAdapter adapter = new CustomTreeViewAdapter(this,
				GetNodes(), R.style.TreeNodeStyle);

		recyclerView.setAdapter(adapter);
	}

	private List<TreeNode> GetNodes() {

		List<TreeNode> nodes = new ArrayList<>();

		CustomTreeNode rootNode1 = new CustomTreeNode();
		rootNode1.Level = 0;
		rootNode1.Text = "Root1";

		List<TreeNode> children = new ArrayList<>();
		CustomTreeNode child1 = new CustomTreeNode();
		child1.Level = 1;
		child1.Text = "Child1";

		CustomTreeNode child2 = new CustomTreeNode();
		child2.Level = 2;
		child2.Text = "Child1";

		children.add(child1);
		children.add(child2);

		rootNode1.addChildren(children);

		CustomTreeNode rootNode2 = new CustomTreeNode();
		rootNode2.Level = 0;
		rootNode2.Text = "Root2";

		nodes.add(rootNode1);
		nodes.add(rootNode2);

		return nodes;
	}
}

package org.jackie.corvallisrecycler_final_project.directory;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jackie.corvallisrecycler_final_project.R;
import org.jackie.corvallisrecycler_final_project.adapters.CategoryAdapter;
import org.jackie.corvallisrecycler_final_project.ui.MainActivity;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CategoryActivity extends ActionBarActivity {

    private Category[] mCategory;

    @InjectView(R.id.recylcerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.CATEGORY);
        mCategory = Arrays.copyOf(parcelables, parcelables.length, Category[].class);

        CategoryAdapter adapter = new CategoryAdapter(this, mCategory);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
    }
}

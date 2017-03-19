package org.jackie.corvallisrecycler_final_project.directory;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jackie.corvallisrecycler_final_project.R;
import org.jackie.corvallisrecycler_final_project.adapters.CategoryAdapter;
import org.jackie.corvallisrecycler_final_project.adapters.SubcategoryAdapter;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SubcategoryActivity extends ActionBarActivity {

    private Subcategory[] mSubcategory;

    @InjectView(R.id.recylcerView2)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(CategoryAdapter.SUBCATEGORY);
        mSubcategory = Arrays.copyOf(parcelables, parcelables.length, Subcategory[].class);

        SubcategoryAdapter adapter = new SubcategoryAdapter(this, mSubcategory);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }
}

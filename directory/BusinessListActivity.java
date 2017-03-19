package org.jackie.corvallisrecycler_final_project.directory;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jackie.corvallisrecycler_final_project.R;
import org.jackie.corvallisrecycler_final_project.adapters.BusinessListAdapter;
import org.jackie.corvallisrecycler_final_project.adapters.SubcategoryAdapter;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BusinessListActivity extends ActionBarActivity {

    private Business[] mBusiness;

    public static final String MAPBUS = "MAPBUS";

    @InjectView(R.id.recylcerView3)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(SubcategoryAdapter.BUSINESS);
        mBusiness = Arrays.copyOf(parcelables, parcelables.length, Business[].class);

        BusinessListAdapter adapter = new BusinessListAdapter(this, mBusiness);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

    }

    @OnClick(R.id.mapButton)
    public void startMapActivity(View view){
        //ButterKnife.inject(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(SubcategoryAdapter.BUSINESS);
        mBusiness = Arrays.copyOf(parcelables, parcelables.length, Business[].class);

        Intent intent2 = new Intent(this, MapActivity.class);
        intent2.putExtra(MAPBUS, mBusiness);
        startActivity(intent2);
    }

}

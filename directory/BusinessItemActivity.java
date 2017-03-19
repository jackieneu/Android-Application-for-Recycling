package org.jackie.corvallisrecycler_final_project.directory;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jackie.corvallisrecycler_final_project.R;
import org.jackie.corvallisrecycler_final_project.adapters.BusinessListAdapter;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BusinessItemActivity extends ActionBarActivity {

    private Business[] mBusiness;
    //public TextView mId;

    @InjectView(R.id.nameLabel4) TextView mName;
    @InjectView(R.id.addressLabel) TextView mAddress;
    @InjectView(R.id.cityLabel) TextView mCity;
    @InjectView(R.id.stateLabel) TextView mState;
    @InjectView(R.id.phoneLabel) TextView mPhone;
    @InjectView(R.id.webLabel) TextView mWeb;
    @InjectView(R.id.hoursLabel) TextView mHours;
    @InjectView(R.id.latitudeLabel) TextView mLatitude;
    @InjectView(R.id.longitudeLabel) TextView mLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_item);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(BusinessListAdapter.BUSINESSITEM);
        mBusiness = Arrays.copyOf(parcelables, parcelables.length, Business[].class);

        mName.setText(mBusiness[0].getName());
        mAddress.setText(mBusiness[0].getStreetAddress());
        if(!(mBusiness[0].getCity().equalsIgnoreCase(""))) {
            mCity.setText(mBusiness[0].getCity() + ", ");
        }else{
            mCity.setText("");
        }
        mState.setText(mBusiness[0].getState());
        mPhone.setText(mBusiness[0].getPhoneNumber());
        mWeb.setText(mBusiness[0].getWebpage());
        mHours.setText(mBusiness[0].getHours());
        mLatitude.setText(mBusiness[0].getLatitude() + "");
        mLongitude.setText(mBusiness[0].getLongitude() + "");
    }


    public void goToPage(View view){
        if (isNetworkAvailable()) {
            goToUrl(mBusiness[0].getWebpage());
        }else {
            Toast.makeText(this, getString(R.string.network_unavailable_message),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }


}
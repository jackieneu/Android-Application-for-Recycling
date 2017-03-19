package org.jackie.corvallisrecycler_final_project.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jackie.corvallisrecycler_final_project.R;
import org.jackie.corvallisrecycler_final_project.directory.Business;
import org.jackie.corvallisrecycler_final_project.directory.BusinessItemActivity;
import org.jackie.corvallisrecycler_final_project.directory.Data;
import org.jackie.corvallisrecycler_final_project.ui.AlertDialogFragment;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Jackie on 5/24/2015.
 */
public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.BusinessListViewHolder> {

    private Business[] mBusiness;
    private Context mContext;

    private Data mData;

    public static final String TAG = CategoryAdapter.class.getSimpleName();
    public static final String BUSINESSITEM = "BUSINESSITEM";

    public BusinessListAdapter(Context context, Business[] business) {
        mBusiness = business;
        mContext = context;
    }

    @Override
    public BusinessListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.businesslist_list_item, viewGroup, false);
        BusinessListViewHolder viewHolder = new BusinessListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BusinessListAdapter.BusinessListViewHolder businessListViewHolder, int i) {
        businessListViewHolder.bindBusinessList(mBusiness[i], i);
    }

    @Override
    public int getItemCount() {
        return mBusiness.length;
    }

    public class BusinessListViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView mName;
        public TextView mId;
        public TextView mNum;
        public TextView mCity;

        public BusinessListViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.nameLabel3);
            mId = (TextView) itemView.findViewById(R.id.idLabel3);
            mNum = (TextView) itemView.findViewById(R.id.numLabel);
            mCity = (TextView) itemView.findViewById(R.id.cityLabel);

            itemView.setOnClickListener(this);
        }

        public void bindBusinessList(Business business, int i) {
            mName.setText(business.getName());
            mId.setText(business.getId() + "");
            mNum.setText(i + 1 + ". ");
            mCity.setText(business.getCity());
        }

        @Override
        public void onClick(View v) {
            //String name = mName.getText().toString();
            String id = mId.getText().toString();

            //String message = String.format("Subcategory: %s, ID: %s", name, id);
            //Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
            String apiUrl = "http://www.recycling-app-13.appspot.com/business/" + id;

            getDirectory(apiUrl);
        }
    }

    private void getDirectory(String apiUrl) {

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();

                        if (response.isSuccessful()) {
                            Log.v(TAG, jsonData);
                            mData = parseBusinessDetail(jsonData);
                            Intent intent = new Intent(mContext, BusinessItemActivity.class); //.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra(BUSINESSITEM, mData.getBusiness());
                            mContext.startActivity(intent);
                        } else {
                            alertUserAboutError();
                        }
                    }
                    catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                    catch (JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else {
            Toast.makeText(mContext, "Network is unavailable!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private Data parseBusinessDetail(String jsonData) throws JSONException{
        Data data = new Data();

        data.setBusiness(getBusinessDetails(jsonData));

        return data;
    }

    private Business[] getBusinessDetails(String jsonData) throws JSONException {

        JSONObject jsonItem = new JSONObject(jsonData);

        Business[] buses = new Business[1];

       // for(int i = 0; i < data.length(); i++){

            //Log.v(TAG, data.length() + "" );
            //JSONObject jsonItem = data.getJSONObject(i);
            Business bus = new Business();

            bus.setName(jsonItem.getString("name"));
            bus.setId(jsonItem.getInt("id"));
            bus.setStreetAddress(jsonItem.getString("streetAddress"));
            bus.setCity(jsonItem.getString("city"));
            bus.setState(jsonItem.getString("state"));
            bus.setZipCode(jsonItem.getString("zipCode"));
            bus.setPhoneNumber(jsonItem.getString("phoneNumber"));
            bus.setHours(jsonItem.getString("hours"));
            bus.setWebpage(jsonItem.getString("webpage"));

            buses[0] = bus;
            //Log.v(TAG, sub.getName().toString());
        //}
        return buses;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        Log.e(TAG, dialog.toString());
    }

}

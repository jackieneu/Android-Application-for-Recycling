package org.jackie.corvallisrecycler_final_project.directory;

/**
 * Created by Jackie on 5/18/2015.
 */
public class Data {

    private Category[] mCategory;
    private Subcategory[] mSubcategory;
    private Business[] mBusiness;

    public Business[] getBusiness() {
        return mBusiness;
    }

    public void setBusiness(Business[] business) {
        mBusiness = business;
    }

    public Subcategory[] getSubcategory() {
        return mSubcategory;
    }

    public void setSubcategory(Subcategory[] subcategory) {
        mSubcategory = subcategory;
    }

    public Category[] getCategory() {
        return mCategory;
    }

    public void setCategory(Category[] category) {
        mCategory = category;
    }
}

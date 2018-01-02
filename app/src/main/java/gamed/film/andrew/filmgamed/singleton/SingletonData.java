package gamed.film.andrew.filmgamed.singleton;

/**
 * Created by ksi on 03-Jul-17.
 */

public class SingletonData
{

    private static SingletonData mInstance = null;

    private String categoryName;

    private SingletonData()
    {
    }

    public static SingletonData getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new SingletonData();
        }
        return mInstance;
    }

    public static SingletonData getmInstance()
    {
        return mInstance;
    }

    public static void setmInstance(SingletonData mInstance)
    {
        SingletonData.mInstance = mInstance;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
}

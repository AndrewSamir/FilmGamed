package gamed.film.andrew.filmgamed.models;

/**
 * Created by andre on 02-Jan-18.
 */

public class ModelCategoryDetails
{
    public String categoryImage, categoryName, categoryId;

    public ModelCategoryDetails()
    {

    }

    public String getCategoryImage()
    {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage)
    {
        this.categoryImage = categoryImage;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }
}

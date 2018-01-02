package gamed.film.andrew.filmgamed.models;

/**
 * Created by andre on 02-Jan-18.
 */

public class ModelItem
{
    public String imageUri,movie,showName;

    public ModelItem()
    {
    }

    public String getImageUri()
    {
        return imageUri;
    }

    public void setImageUri(String imageUri)
    {
        this.imageUri = imageUri;
    }

    public String getMovie()
    {
        return movie;
    }

    public void setMovie(String movie)
    {
        this.movie = movie;
    }

    public String getShowName()
    {
        return showName;
    }

    public void setShowName(String showName)
    {
        this.showName = showName;
    }
}

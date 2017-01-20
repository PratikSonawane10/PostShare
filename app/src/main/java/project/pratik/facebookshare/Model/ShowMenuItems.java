package project.pratik.facebookshare.Model;

import project.pratik.facebookshare.Singleton.Image_Url;

public class ShowMenuItems {

    public String firstImagePath;
    public String Userid;
    public String title;
    public String description;
    public String listId;
    public String listIdRemark;


    public ShowMenuItems() {
    }

    public ShowMenuItems(String Userid,String firstImagePath, String title, String description, String listId ) {

        this.firstImagePath = firstImagePath;
        this.Userid = Userid;
        this.description = description;
        this.title = title;
        this.listId = listId;

    }

    public String getFirstImagePath() {
        return firstImagePath;
    }

    public void setFirstImagePath(String firstImagePath) {
        this.firstImagePath =  Image_Url.getUrl()+"homework_images/"+firstImagePath;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String Userid) {
        this.Userid = Userid;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getlistId() {
        return listId;
    }
    public void setlistId(String listId) {
        this.listId = listId;
    }

}

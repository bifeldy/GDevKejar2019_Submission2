package id.ac.umn.made_basiliusbias_submission2.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable {

    private int photo;
    private String title;
    private String description;
    private double score;
    private String type;

    public Film() {}

    public Film(int photo, String title, String description, double score, String type) {
        this.photo = photo;
        this.title = title;
        this.description = description;
        this.score = score;
        this.type = type;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getScore() { return score; }

    public void setScore(double score) { this.score = score; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.photo);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeDouble(this.score);
        dest.writeString(this.type);
    }

    private Film(Parcel in) {
        this.photo = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.score = in.readDouble();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}

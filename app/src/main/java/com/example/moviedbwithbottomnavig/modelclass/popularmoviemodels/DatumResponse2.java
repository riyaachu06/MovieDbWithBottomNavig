
package com.example.moviedbwithbottomnavig.modelclass.popularmoviemodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumResponse2 implements Parcelable {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    protected DatumResponse2(Parcel in) {
        if (in.readByte() == 0) {
            page = null;
        } else {
            page = in.readInt();
        }
        if (in.readByte() == 0) {
            totalResults = null;
        } else {
            totalResults = in.readInt();
        }
        if (in.readByte() == 0) {
            totalPages = null;
        } else {
            totalPages = in.readInt();
        }
    }

    public static final Creator<DatumResponse2> CREATOR = new Creator<DatumResponse2>() {
        @Override
        public DatumResponse2 createFromParcel(Parcel in) {
            return new DatumResponse2(in);
        }

        @Override
        public DatumResponse2[] newArray(int size) {
            return new DatumResponse2[size];
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (page == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(page);
        }
        if (totalResults == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalResults);
        }
        if (totalPages == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalPages);
        }
    }
}

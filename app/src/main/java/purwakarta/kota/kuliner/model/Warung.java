package purwakarta.kota.kuliner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Warung {

    @SerializedName("warungs")
    @Expose
    private List<DataWarung> warungs = new ArrayList<DataWarung>();

    public List<DataWarung> getWarungs() {
        return warungs;
    }

    public void setWarungs(List<DataWarung> warungs) {
        this.warungs = warungs;
    }

}
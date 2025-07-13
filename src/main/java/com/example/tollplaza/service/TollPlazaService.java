import com.example.tollplaza.util.PincodeGeoUtil;
import com.example.tollplaza.util.TollPlazaCsvLoader;

public class TollPlazaService {

    private final TollPlazaCsvLoader csvLoader;

    public TollPlazaService(TollPlazaCsvLoader csvLoader) {
        this.csvLoader = csvLoader;
    }
} 
package com.bagus2x.serverapp.util;

import com.bagus2x.serverapp.country.Country;
import com.bagus2x.serverapp.region.Region;

import java.util.ArrayList;
import java.util.List;

public class DataDummy {
    public static List<Region> createRegions() {
        List<Region> regions = new ArrayList<>();

        Region asia = new Region();
        asia.setName("Asia");

        Region africa = new Region();
        africa.setName("Africa");

        Region europe = new Region();
        europe.setName("Europe");

        regions.add(asia);
        regions.add(africa);
        regions.add(europe);

        return regions;
    }

    public static List<Country> createCountries(List<Region> regions) {
        List<Country> countries = new ArrayList<>();

        // Country 1
        Country indonesia = new Country();
        indonesia.setCode("ID");
        indonesia.setName("Indonesia");
        indonesia.setRegion(regions.get(0));

        // Country 2
        Country malaysia = new Country();
        malaysia.setCode("MY");
        malaysia.setName("Malaysia");
        malaysia.setRegion(regions.get(0));

        // Country 3
        Country nigeria = new Country();
        nigeria.setCode("NG");
        nigeria.setName("Nigeria");
        nigeria.setRegion(regions.get(1));

        // Country 4
        Country egypt = new Country();
        egypt.setCode("EG");
        egypt.setName("Egypt");
        egypt.setRegion(regions.get(1));

        // Country 5
        Country france = new Country();
        france.setCode("FR");
        france.setName("France");
        france.setRegion(regions.get(2));

        // Country 6
        Country germany = new Country();
        germany.setCode("DE");
        germany.setName("Germany");
        germany.setRegion(regions.get(2));

        countries.add(indonesia);
        countries.add(malaysia);
        countries.add(nigeria);
        countries.add(egypt);
        countries.add(france);
        countries.add(germany);

        return countries;
    }
}

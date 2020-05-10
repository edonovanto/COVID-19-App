package com.novanto.covid.cases;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Covid19Case {

    @SerializedName("Global")
    public Global global = null;
    @SerializedName("Countries")
    public List<Countries> countries = null;
    @SerializedName("Date")
    private String Date;

    public Global getGlobal() {
        return global;
    }

    public List<Countries> getCountries() {
        return countries;
    }

    public class Global{
        @SerializedName("NewConfirmed")
        private Integer NewConfirmed;

        @SerializedName("TotalConfirmed")
        private Integer TotalConfirmed;

        @SerializedName("NewDeaths")
        private Integer NewDeaths;

        @SerializedName("TotalDeaths")
        private Integer TotalDeaths;

        @SerializedName("NewRecovered")
        private Integer NewRecovered;

        @SerializedName("TotalRecovered")
        private Integer TotalRecovered;

        public Global(Integer newConfirmed, Integer totalConfirmed, Integer newDeaths, Integer totalDeaths, Integer newRecovered, Integer totalRecovered) {
            NewConfirmed = newConfirmed;
            TotalConfirmed = totalConfirmed;
            NewDeaths = newDeaths;
            TotalDeaths = totalDeaths;
            NewRecovered = newRecovered;
            TotalRecovered = totalRecovered;
        }

        public Integer getNewConfirmed() {
            return NewConfirmed;
        }

        public Integer getTotalConfirmed() {
            return TotalConfirmed;
        }

        public Integer getNewDeaths() {
            return NewDeaths;
        }

        public Integer getTotalDeaths() {
            return TotalDeaths;
        }

        public Integer getNewRecovered() {
            return NewRecovered;
        }

        public Integer getTotalRecovered() {
            return TotalRecovered;
        }
    }

    public class Countries{
        @SerializedName("Country")
        private String Country;

        @SerializedName("CountryCode")
        private String CountryCode;

        @SerializedName("Slug")
        private String Slug;

        @SerializedName("NewConfirmed")
        private Integer NewConfirmed;

        @SerializedName("TotalConfirmed")
        private Integer TotalConfirmed;

        @SerializedName("NewDeaths")
        private Integer NewDeaths;

        @SerializedName("TotalDeaths")
        private Integer TotalDeaths;

        @SerializedName("NewRecovered")
        private Integer NewRecovered;

        @SerializedName("TotalRecovered")
        private Integer TotalRecovered;

        @SerializedName("Date")
        private String Date;

        public Countries(String country, String countryCode, String slug, Integer newConfirmed, Integer totalConfirmed, Integer newDeaths, Integer totalDeaths, Integer newRecovered, Integer totalRecovered, String date) {
            Country = country;
            CountryCode = countryCode;
            Slug = slug;
            NewConfirmed = newConfirmed;
            TotalConfirmed = totalConfirmed;
            NewDeaths = newDeaths;
            TotalDeaths = totalDeaths;
            NewRecovered = newRecovered;
            TotalRecovered = totalRecovered;
            Date = date;
        }

        public String getCountry() {
            return Country;
        }

        public String getCountryCode() {
            return CountryCode;
        }

        public String getSlug() {
            return Slug;
        }

        public Integer getNewConfirmed() {
            return NewConfirmed;
        }

        public Integer getTotalConfirmed() {
            return TotalConfirmed;
        }

        public Integer getNewDeaths() {
            return NewDeaths;
        }

        public Integer getTotalDeaths() {
            return TotalDeaths;
        }

        public Integer getNewRecovered() {
            return NewRecovered;
        }

        public Integer getTotalRecovered() {
            return TotalRecovered;
        }

        public String getDate() {
            return Date;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "berhasil";
    }
}

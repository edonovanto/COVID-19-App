package com.novanto.covid.cases;

import com.google.gson.annotations.SerializedName;

public class Covid19Case {

    @SerializedName("attributes")
    public Covid19Case.Global global = null;
    public Covid19Case.Countries countries = null;

    public Global getGlobal() {
        return global;
    }

    public Countries getCountries() {
        return countries;
    }

    public class Global{
        @SerializedName("NewConfirmed")
        private String NewConfirmed;

        @SerializedName("TotalConfirmed")
        private String TotalConfirmed;

        @SerializedName("NewDeaths")
        private String NewDeaths;

        @SerializedName("TotalDeaths")
        private String TotalDeaths;

        @SerializedName("NewRecovered")
        private String NewRecovered;

        @SerializedName("TotalRecovered")
        private String TotalRecovered;

        public Global(String newConfirmed, String totalConfirmed, String newDeaths, String totalDeaths, String newRecovered, String totalRecovered) {
            NewConfirmed = newConfirmed;
            TotalConfirmed = totalConfirmed;
            NewDeaths = newDeaths;
            TotalDeaths = totalDeaths;
            NewRecovered = newRecovered;
            TotalRecovered = totalRecovered;
        }


        public String getNewConfirmed() {
            return NewConfirmed;
        }

        public String getTotalConfirmed() {
            return TotalConfirmed;
        }

        public String getNewDeaths() {
            return NewDeaths;
        }

        public String getTotalDeaths() {
            return TotalDeaths;
        }

        public String getNewRecovered() {
            return NewRecovered;
        }

        public String getTotalRecovered() {
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
        private String NewConfirmed;

        @SerializedName("TotalConfirmed")
        private String TotalConfirmed;

        @SerializedName("NewDeaths")
        private String NewDeaths;

        @SerializedName("TotalDeaths")
        private String TotalDeaths;

        @SerializedName("NewRecovered")
        private String NewRecovered;

        @SerializedName("TotalRecovered")
        private String TotalRecovered;

        @SerializedName("Date")
        private String Date;

        public Countries(String country, String countryCode, String slug, String newConfirmed, String totalConfirmed, String newDeaths, String totalDeaths, String newRecovered, String totalRecovered, String date) {
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

        public String getNewConfirmed() {
            return NewConfirmed;
        }

        public String getTotalConfirmed() {
            return TotalConfirmed;
        }

        public String getNewDeaths() {
            return NewDeaths;
        }

        public String getTotalDeaths() {
            return TotalDeaths;
        }

        public String getNewRecovered() {
            return NewRecovered;
        }

        public String getTotalRecovered() {
            return TotalRecovered;
        }

        public String getDate() {
            return Date;
        }
    }
}

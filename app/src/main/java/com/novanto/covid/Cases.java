package com.novanto.covid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.novanto.covid.cases.Covid19Api;
import com.novanto.covid.cases.Covid19Case;

public class Cases extends Fragment {
    private List<String> list = new ArrayList<String>();
    private TextView namaNegara;
    private TextView positifNegara;
    private TextView sembuhNegara;
    private TextView meninggalNegara;
    private TextView totalPositif;
    private TextView totalSembuh;
    private TextView totalMeninggal;
    private Covid19Case covid19Case;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cases_frag, container, false);

        //menambah "Pilih provinsi" ke array list
        list.add(getString(R.string.cases_prompt));


        covid19Case = new Covid19Case();
        getCovid19Cases();

        namaNegara = view.findViewById(R.id.namaNegara);
        positifNegara = view.findViewById(R.id.positifNegara);
        sembuhNegara = view.findViewById(R.id.sembuhNegara);
        meninggalNegara = view.findViewById(R.id.meninggalNegara);

        totalPositif = view.findViewById(R.id.totalPositif);
        totalSembuh = view.findViewById(R.id.totalSembuh);
        totalMeninggal = view.findViewById(R.id.totalMeninggal);

        //membuat spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.cases_spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        //set spinner yang dipilih
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    //set text "-" saat memilih "pilih provinsi"
                    namaNegara.setText("-");
                    positifNegara.setText("-");
                    sembuhNegara.setText("-");
                    meninggalNegara.setText("-");

                }else{
                    //set text saat user memilih suatu provinsi
                    namaNegara.setText(covid19Case.getCountries().get(position-1).getCountry());

                    String totalPositif = NumberFormat.getInstance().format(covid19Case.getCountries().get(position-1).getTotalConfirmed());
                    String totalSembuh = NumberFormat.getInstance().format(covid19Case.getCountries().get(position-1).getTotalRecovered());
                    String totalMeninggal = NumberFormat.getInstance().format(covid19Case.getCountries().get(position-1).getTotalDeaths());

                    positifNegara.setText(totalPositif);
                    sembuhNegara.setText(totalSembuh);
                    meninggalNegara.setText(totalMeninggal);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //set text saat user tidak memilih apapun
                namaNegara.setText("-");
                positifNegara.setText("-");
                sembuhNegara.setText("-");
                meninggalNegara.setText("-");
            }
        });

        //set text menjadi loading
        totalPositif.setText("Loading...");
        totalSembuh.setText("Loading...");
        totalMeninggal.setText("Loading...");




        return  view;
    }


    private void getCovid19Cases(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Covid19Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Covid19Api covid19Api = retrofit.create(Covid19Api.class);

        Call<Covid19Case> callCovid19 = covid19Api.getAllCovid19Cases();

        callCovid19.enqueue(new Callback<Covid19Case>() {
            @Override
            public void onResponse(Call<Covid19Case> call, Response<Covid19Case> response) {
                covid19Case = response.body();

                for (int i = 0; i < covid19Case.getCountries().size(); i++){
                    list.add(covid19Case.getCountries().get(i).getCountry());
                }
                totalPositif.setText(NumberFormat.getInstance().format(covid19Case.getGlobal().getTotalConfirmed()));
                totalSembuh.setText(NumberFormat.getInstance().format(covid19Case.getGlobal().getTotalRecovered()));
                totalMeninggal.setText(NumberFormat.getInstance().format(covid19Case.getGlobal().getTotalDeaths()));
            }

            @Override
            public void onFailure(Call<Covid19Case> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

}

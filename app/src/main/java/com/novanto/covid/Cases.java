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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cases extends Fragment {
    private List<String> list = new ArrayList<String>();
    private List<ProvinceCase> provinceCaseList;
    private List<IndonesiaCase> indonesiaCaseList;
    private TextView namaProvinsi;
    private TextView positifProvinsi;
    private TextView sembuhProvinsi;
    private TextView meninggalProvinsi;
    private TextView totalPositif;
    private TextView totalSembuh;
    private TextView totalMeninggal;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cases_frag, container, false);
        list.add("Pilih Provinsi");
        getProvinceCases();

        namaProvinsi = view.findViewById(R.id.namaProvinsi);
        positifProvinsi = view.findViewById(R.id.positifProvinsi);
        sembuhProvinsi = view.findViewById(R.id.sembuhProvinsi);
        meninggalProvinsi = view.findViewById(R.id.meninggalProvinsi);

        totalPositif = view.findViewById(R.id.totalPositif);
        totalSembuh = view.findViewById(R.id.totalSembuh);
        totalMeninggal = view.findViewById(R.id.totalMeninggal);

        Spinner spinner = (Spinner) view.findViewById(R.id.cases_spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    namaProvinsi.setText("-");
                    positifProvinsi.setText("-");
                    sembuhProvinsi.setText("-");
                    meninggalProvinsi.setText("-");

                }else{
                    namaProvinsi.setText(provinceCaseList.get(position-1).getAttributes().getProvinsi());
                    positifProvinsi.setText(provinceCaseList.get(position-1).getAttributes().getKasus_Posi());
                    sembuhProvinsi.setText(provinceCaseList.get(position-1).getAttributes().getKasus_Semb());
                    meninggalProvinsi.setText(provinceCaseList.get(position-1).getAttributes().getKasus_Meni());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                namaProvinsi.setText("-");
                positifProvinsi.setText("-");
                sembuhProvinsi.setText("-");
                meninggalProvinsi.setText("-");
            }
        });

        getIndonesiaCases();



        return  view;
    }

    private void getProvinceCases(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KawalCoronaApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KawalCoronaApi kawalCoronaApi = retrofit.create(KawalCoronaApi.class);

        Call<List<ProvinceCase>> callProvinsi = kawalCoronaApi.getListProvinsi();

        callProvinsi.enqueue(new Callback<List<ProvinceCase>>() {
            @Override
            public void onResponse(Call<List<ProvinceCase>> call, Response<List<ProvinceCase>> response) {
                provinceCaseList = response.body();

                for (int i = 0; i < provinceCaseList.size(); i++){
                    list.add(provinceCaseList.get(i).getAttributes().getProvinsi());
                }
            }

            @Override
            public void onFailure(Call<List<ProvinceCase>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void getIndonesiaCases(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KawalCoronaApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KawalCoronaApi kawalCoronaApi = retrofit.create(KawalCoronaApi.class);
        Call<List<IndonesiaCase>> callIndonesia = kawalCoronaApi.getListIndonesia();

        callIndonesia.enqueue(new Callback<List<IndonesiaCase>>() {
            @Override
            public void onResponse(Call<List<IndonesiaCase>> call, Response<List<IndonesiaCase>> response) {
                indonesiaCaseList = response.body();

                totalPositif.setText(indonesiaCaseList.get(0).getPositif());
                totalSembuh.setText(indonesiaCaseList.get(0).getSembuh());
                totalMeninggal.setText(indonesiaCaseList.get(0).getMeninggal());
            }

            @Override
            public void onFailure(Call<List<IndonesiaCase>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}

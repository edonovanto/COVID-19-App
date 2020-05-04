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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cases extends Fragment {
    private List<String> list = new ArrayList<String>();
    private List<Case> caseList;
    private TextView namaProvinsi;
    private TextView positifProvinsi;
    private TextView sembuhProvinsi;
    private TextView meninggalProvinsi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cases_frag, container, false);
        namaProvinsi = view.findViewById(R.id.namaProvinsi);
        positifProvinsi = view.findViewById(R.id.positifProvinsi);
        sembuhProvinsi = view.findViewById(R.id.sembuhProvinsi);
        meninggalProvinsi = view.findViewById(R.id.meninggalProvinsi);

        String defaultString = "Pilih Provinsi";
//        String[] values = {"Pilih Provinsi","DKI Jakarta", "Bandung", "Yogyakarta"};
        list.add("Pilih Provinsi");
        getCases();

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
                    namaProvinsi.setText(caseList.get(position-1).getAttributes().getProvinsi());
                    positifProvinsi.setText(caseList.get(position-1).getAttributes().getKasus_Posi());
                    sembuhProvinsi.setText(caseList.get(position-1).getAttributes().getKasus_Semb());
                    meninggalProvinsi.setText(caseList.get(position-1).getAttributes().getKasus_Meni());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return  view;
    }

    private void getCases(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KawalCoronaApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KawalCoronaApi kawalCoronaApi = retrofit.create(KawalCoronaApi.class);

        Call<List<Case>> callProvinsi = kawalCoronaApi.getListProvinsi();

        callProvinsi.enqueue(new Callback<List<Case>>() {
            @Override
            public void onResponse(Call<List<Case>> call, Response<List<Case>> response) {
                caseList = response.body();

                for (int i=0; i < caseList.size(); i++){
                    list.add(caseList.get(i).getAttributes().getProvinsi());
                }
//                String responseText = caseList.get(0).getAttributes().getProvinsi();
//                Toast.makeText(getContext(),responseText,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Case>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}

package com.github.anovosvit.covidapp.countrylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.anovosvit.covidapp.R;
import com.github.anovosvit.covidapp.model.CountryInfo;

import java.util.List;

public class CovidAdapter extends RecyclerView.Adapter<CovidAdapter.CovidViewHolder> {

    private CovidListActivity context;
    private List<CountryInfo> countries;

    public void setCountries(List<CountryInfo> countries) {
        this.countries = countries;
    }

    public List<CountryInfo> getCountries() {
        return countries;
    }

    public CovidAdapter(CovidListActivity context, List<CountryInfo> countries) {
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public CovidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_item, parent, false);
        return new CovidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidViewHolder holder, int position) {
        CountryInfo currentCountry = countries.get(position);
        String name = currentCountry.getCountryName();
        Integer confirmedCases = currentCountry.getTotalConfirmed();
        Integer recoveredCases = currentCountry.getTotalRecovered();

        holder.countryName.setText(name);
        holder.totalRecovered.setText(recoveredCases.toString());
        holder.totalConfirmed.setText(confirmedCases.toString());

        holder.itemView.setOnClickListener(v -> context.onCountryItemClick(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class CovidViewHolder extends RecyclerView.ViewHolder {

        TextView countryName;
        TextView totalConfirmed;
        TextView totalRecovered;

        public CovidViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryName);
            totalConfirmed = itemView.findViewById(R.id.totalConfirmedTextView);
            totalRecovered = itemView.findViewById(R.id.totalRecoveredTextView);
        }
    }
}

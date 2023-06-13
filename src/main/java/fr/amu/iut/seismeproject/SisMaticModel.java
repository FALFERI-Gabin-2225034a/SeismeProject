package fr.amu.iut.seismeproject;

import com.gluonhq.maps.MapLayer;
import javafx.scene.chart.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SisMaticModel {
    private File fileCSV = new File("");

    private ArrayList<String> dataKeys;

    private Map<String, ArrayList<String>> data;

    private ArrayList<MapLayer> layerChildrens;

    private BarChart<String, Number> biggestSeismicEventChart;
    private BarChart<String, Number> smallestSeismicEventChart;
    private LineChart<String, Number> timeEvolutionChart;
    private StackedBarChart<String, Number> seismicMagnitudeChart;

    public SisMaticModel() {
        data = new HashMap<>();
        dataKeys = new ArrayList<>();
        layerChildrens = new ArrayList<>();
        biggestSeismicEventChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        smallestSeismicEventChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        timeEvolutionChart = new LineChart<>(new CategoryAxis(), new NumberAxis());
        seismicMagnitudeChart = new StackedBarChart<>(new CategoryAxis(), new NumberAxis());
    }

    public void setFileCSV(File fileCSV) {
        this.fileCSV = fileCSV;
    }

    public File getFileCSV() {
        return fileCSV;
    }

    public void setDataKeys(ArrayList<String> dataKeys) {
        this.dataKeys = dataKeys;
    }

    public ArrayList<String> getDataKeys() {
        return dataKeys;
    }

    public void setData(Map<String, ArrayList<String>> data) {
        this.data = data;
    }

    public Map<String, ArrayList<String>> getData() {
        return data;
    }

    public ArrayList<MapLayer> getLayerChildrens() {
        return layerChildrens;
    }

    public void setLayerChildrens(ArrayList<MapLayer> layerChildrens) {
        this.layerChildrens = layerChildrens;
    }

    public BarChart<String, Number> getBiggestSeismicEventChart() {
        return biggestSeismicEventChart;
    }

    public BarChart<String, Number> getSmallestSeismicEventChart() {
        return smallestSeismicEventChart;
    }

    public LineChart<String, Number> getTimeEvolutionChart() {
        return timeEvolutionChart;
    }

    public StackedBarChart<String, Number> getSeismicMagnitudeChart() {
        return seismicMagnitudeChart;
    }

    // update pour les graphiques à partir des données CSV
    public void updateChartsFromCSVData() {
        if (data.containsKey("magnitude") && data.containsKey("date")) {
            ArrayList<String> magnitudes = data.get("magnitude");
            ArrayList<String> dates = data.get("date");

            // graphique du plus gros séisme
            updateBiggestSeismicEventChart(magnitudes, dates);

            // graphique du plus petit séisme
            updateSmallestSeismicEventChart(magnitudes, dates);

            // graphique de l'évolution dans le temps
            updateTimeEvolutionChart(magnitudes, dates);

            // graphique de la magnitude des séismes
            updateSeismicMagnitudeChart(magnitudes, dates);
        }
    }

    private void updateBiggestSeismicEventChart(ArrayList<String> magnitudes, ArrayList<String> dates) {
        biggestSeismicEventChart.getData().clear();

        // Recherche du plus gros séisme
        double maxMagnitude = Double.MIN_VALUE;
        String maxMagnitudeDate = "";

        for (int i = 0; i < magnitudes.size(); i++) {
            double magnitude = Double.parseDouble(magnitudes.get(i));
            if (magnitude > maxMagnitude) {
                maxMagnitude = magnitude;
                maxMagnitudeDate = dates.get(i);
            }
        }

        // Ajout du plus gros séisme au graphique
        CategoryAxis xAxis = (CategoryAxis) biggestSeismicEventChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) biggestSeismicEventChart.getYAxis();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(maxMagnitudeDate, maxMagnitude));
        biggestSeismicEventChart.getData().add(series);
    }

    private void updateSmallestSeismicEventChart(ArrayList<String> magnitudes, ArrayList<String> dates) {
        smallestSeismicEventChart.getData().clear();

        // Recherche du plus petit séisme
        double minMagnitude = Double.MAX_VALUE;
        String minMagnitudeDate = "";

        for (int i = 0; i < magnitudes.size(); i++) {
            double magnitude = Double.parseDouble(magnitudes.get(i));
            if (magnitude < minMagnitude) {
                minMagnitude = magnitude;
                minMagnitudeDate = dates.get(i);
            }
        }

        // Ajout du plus petit séisme au graphique
        CategoryAxis xAxis = (CategoryAxis) smallestSeismicEventChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) smallestSeismicEventChart.getYAxis();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(minMagnitudeDate, minMagnitude));
        smallestSeismicEventChart.getData().add(series);
    }

    private void updateTimeEvolutionChart(ArrayList<String> magnitudes, ArrayList<String> dates) {
        timeEvolutionChart.getData().clear();

        // Ajout des données au graphique
        CategoryAxis xAxis = (CategoryAxis) timeEvolutionChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) timeEvolutionChart.getYAxis();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < magnitudes.size(); i++) {
            series.getData().add(new XYChart.Data<>(dates.get(i), Double.parseDouble(magnitudes.get(i))));
        }
        timeEvolutionChart.getData().add(series);
    }

    private void updateSeismicMagnitudeChart(ArrayList<String> magnitudes, ArrayList<String> dates) {
        seismicMagnitudeChart.getData().clear();

        // Ajout des données au graphique
        CategoryAxis xAxis = (CategoryAxis) seismicMagnitudeChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) seismicMagnitudeChart.getYAxis();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < magnitudes.size(); i++) {
            series.getData().add(new XYChart.Data<>(dates.get(i), Double.parseDouble(magnitudes.get(i))));
        }
        seismicMagnitudeChart.getData().add(series);
    }
}
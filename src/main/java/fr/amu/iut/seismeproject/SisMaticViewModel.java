package fr.amu.iut.seismeproject;

public class SisMaticViewModel {
    private SisMaticModel model;

    public SisMaticViewModel() {
        this(new SisMaticModel());
    }

    public SisMaticViewModel(SisMaticModel model) {
        this.model = model;
    }
}

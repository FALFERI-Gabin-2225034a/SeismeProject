package fr.amu.iut.seismeproject;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** Affiche un point rouge sur la carte */
public class CustomMarkerLayer extends MapLayer {

    private final MapPoint mapPoint;
    private final Circle circle;

    /**
     * @param mapPoint le point (latitude et longitude) où afficher le cercle
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomMarkerLayer(MapPoint mapPoint, Double intensity) {
        this.mapPoint = mapPoint;

        /* Création du Cercle */
        this.circle = new Circle(10);
        this.circle.setFill(Color.TRANSPARENT);
        this.circle.setStrokeWidth(3);

        if (intensity.isNaN() || intensity < 2)
            this.circle.setStroke(Color.DARKBLUE);
        else if (intensity < 3)
            this.circle.setStroke(Color.BLUE);
        else if (intensity < 4)
            this.circle.setStroke(Color.CYAN);
        else if (intensity < 5)
            this.circle.setStroke(Color.LIGHTGREEN);
        else if (intensity < 6)
            this.circle.setStroke(Color.YELLOW);
        else if (intensity < 7)
            this.circle.setStroke(Color.ORANGE);
        else if (intensity < 8)
            this.circle.setStroke(Color.RED);
        else if (intensity < 9)
            this.circle.setStroke(Color.PINK);
        else
            this.circle.setStroke(Color.PURPLE);

        /* Ajoute le cercle au MapLayer */
        this.getChildren().add(circle);
    }

    /* La fonction est appelée à chaque rafraichissement de la carte */
    @Override
    protected void layoutLayer() {
        /* Conversion du MapPoint vers Point2D */
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        /* Déplace le cercle selon les coordonnées du point */
        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());
    }
}
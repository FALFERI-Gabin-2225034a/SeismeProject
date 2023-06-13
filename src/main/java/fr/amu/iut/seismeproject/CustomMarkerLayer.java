package fr.amu.iut.seismeproject;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** Displays a red circle marker on the map */
public class CustomMarkerLayer extends MapLayer {

    private final MapPoint mapPoint;
    private final Circle circle;

    /**
     * @param mapPoint  The point (latitude and longitude) where the circle should be displayed
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomMarkerLayer(MapPoint mapPoint, Double intensity) {
        this.mapPoint = mapPoint;

        /* Create the Circle */
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

        /* Add the circle to the MapLayer */
        this.getChildren().add(circle);
    }

    /* This function is called whenever the map is refreshed */
    @Override
    protected void layoutLayer() {
        /* Convert MapPoint to Point2D */
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        /* Move the circle based on the point coordinates */
        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());
    }
}
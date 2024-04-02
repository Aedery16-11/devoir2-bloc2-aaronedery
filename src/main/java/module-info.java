module sio.devoir2sio1b {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens sio.devoir2sio1b.Model;

    opens sio.devoir2sio1b to javafx.fxml;
    exports sio.devoir2sio1b;
}
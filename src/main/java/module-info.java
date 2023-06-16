module com.example.digitalhealthcaresystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.digitalhealthcaresystem to javafx.fxml;
    exports com.example.digitalhealthcaresystem;
    exports com.example.digitalhealthcaresystem.Archives;
    opens com.example.digitalhealthcaresystem.Archives to javafx.fxml;
}
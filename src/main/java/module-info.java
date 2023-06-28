module com.example.digitalhealthcaresystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.digitalhealthcaresystem to javafx.fxml;
    exports com.example.digitalhealthcaresystem;
    opens com.example.digitalhealthcaresystem.Archives to javafx.fxml;
    exports com.example.digitalhealthcaresystem.PatientForms;
    opens com.example.digitalhealthcaresystem.PatientForms to javafx.fxml;
    exports com.example.digitalhealthcaresystem.TreatmentCourseForms;
    opens com.example.digitalhealthcaresystem.TreatmentCourseForms to javafx.fxml;
    exports com.example.digitalhealthcaresystem.PatientHistoryForms;
    opens com.example.digitalhealthcaresystem.PatientHistoryForms to javafx.fxml;
    exports com.example.digitalhealthcaresystem.DataStorageControllers;
    opens com.example.digitalhealthcaresystem.DataStorageControllers to javafx.fxml;
    exports com.example.digitalhealthcaresystem.MedicalReviewForms;
    opens com.example.digitalhealthcaresystem.MedicalReviewForms to javafx.fxml;
}
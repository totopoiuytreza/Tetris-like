module com.example.tetrislike {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.tetrislike to javafx.fxml;
    exports com.example.tetrislike;
}
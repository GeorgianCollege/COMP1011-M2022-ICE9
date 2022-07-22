package ca.georgiancollege.comp1011m2022ice9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable
{

    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Movie> resultsListView;

    @FXML
    private TextField searchTextField;

    @FXML
    void searchButtonClicked(ActionEvent event)
    {
        var searchResults = APIManager.Instance().getMovieFromOMDBBySearchTerm(searchTextField.getText());
        resultsListView.getItems().clear();
        if(searchResults.getMovies() != null)
        {
            resultsListView.getItems().addAll(searchResults.getMovies());

            resultsListView.getSelectionModel().select(0);
        }
        else
        {
            // movie was not found - maybe output to a message label of some kind
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        var posterNotFoundImage = new Image("https://trailerfailure.com/img/images/missingConverPhoto.jpg");

        resultsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldMovieSelected, newMovieSelected) ->{
            System.out.println(newMovieSelected);
            try
            {

                    imageView.setImage(new Image(newMovieSelected.getPoster()));



            }catch(IllegalArgumentException e)
            {
                imageView.setImage(posterNotFoundImage);
            }
        });
    }
}
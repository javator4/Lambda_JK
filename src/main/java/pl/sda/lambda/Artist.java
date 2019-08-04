package pl.sda.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Artist {
    private String name;
    private List<Artist> members;
    private String nationality;

    public Artist(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;

    }

}

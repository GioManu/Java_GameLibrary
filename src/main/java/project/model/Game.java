package main.java.project.model;
import javax.persistence.*;

@Entity
@Table(name="`Game`")
public class Game {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column (name ="developer")
    private String developer;
    @Column (name = "title")
    private String title;
    @Column (name = "genre")
    private String genre;

    @Column (name ="isSingle")
    private boolean isSinglePlayer;
    @Column (name = "isMulti")
    private boolean isMulti;
    @Column (name = "isCoop")
    private boolean isCoop;

    @Column (name = "age_rest")
    private String ageRest;
    @Column (name = "description")
    private String descript;

    public Game(){}

    public Game(String developer, String title, String genre, boolean isSinglePlayer, boolean isMulti, boolean isCoop, String ageRest, String descript) {
        this.developer = developer;
        this.title = title;
        this.genre = genre;
        this.isSinglePlayer = isSinglePlayer;
        this.isMulti = isMulti;
        this.isCoop = isCoop;
        this.ageRest = ageRest;
        this.descript = descript;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isSinglePlayer() {
        return isSinglePlayer;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        isSinglePlayer = singlePlayer;
    }

    public boolean isMulti() {
        return isMulti;
    }

    public void setMulti(boolean multi) {
        isMulti = multi;
    }

    public boolean isCoop() {
        return isCoop;
    }

    public void setCoop(boolean coop) {
        isCoop = coop;
    }

    public String getAgeRest() {
        return ageRest;
    }

    public void setAgeRest(String ageRest) {
        this.ageRest = ageRest;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}

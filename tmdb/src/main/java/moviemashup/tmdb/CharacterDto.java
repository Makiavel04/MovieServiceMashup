package moviemashup.tmdb;

import java.net.URL;

public class CharacterDto {

    private String characterName;

    private URL imgurl;

    private String actorName;

    private String birthday;

    private String deathday = null;

    private String placeOfBirth;

    public CharacterDto(){
    }

    public CharacterDto(String characterName, URL imgurl, String actorName, String birthday, String placeOfBirth) {
        this.characterName = characterName;
        this.imgurl = imgurl;
        this.actorName = actorName;
        this.birthday = birthday;
        this.placeOfBirth = placeOfBirth;
    }

    public CharacterDto(String characterName, URL imgurl, String actorName, String birthday, String deathday, String placeOfBirth) {
        this(characterName,imgurl,actorName,birthday,placeOfBirth);
        this.deathday = deathday;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public URL getImgurl() {
        return imgurl;
    }

    public void setImgurl(URL imgurl) {
        this.imgurl = imgurl;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @Override
    public String toString() {
        return "CharacterDto{" +
                "characterName='" + characterName + '\'' +
                ", imgurl=" + imgurl +
                ", actorName='" + actorName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", deathday='" + deathday + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                '}';
    }
}

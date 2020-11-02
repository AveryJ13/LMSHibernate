package com.ss.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "tbl_genre")
public class Genre implements Serializable{

	
	private static final long serialVersionUID = -6031491835959242173L;
//    private Integer genreId;
//    private String genreName;
//
//    public Genre(Integer genreId, String genreName){
//        this.genreId = genreId;
//        this.genreName = genreName;
//    }
//
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id")
	private Integer genre_id;
	
	@Column(name = "genre_name")
	@NonNull
	private String genre_name;
	
	
	
  

	public Integer getGenreId(){
        return genre_id;
    }

    public String getGenreName(){
        return genre_name;
    }

    public void setGenreId(Integer genre_id){
        this.genre_id = genre_id;
    }

    public void setGenreName(String genre_name){
        this.genre_name = genre_name;
    }
	
	
}

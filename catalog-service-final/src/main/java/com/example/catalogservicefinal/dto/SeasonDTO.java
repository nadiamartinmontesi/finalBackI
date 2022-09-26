package com.example.catalogservicefinal.dto;

import java.util.List;

public class SeasonDTO {

    private Integer id;
    private Integer seasonNumber;
    private List<ChapterDTO> chapters;

    public SeasonDTO() {
        //No-args constructor
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public List<ChapterDTO> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterDTO> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "SeasonDTO{" +
                "id=" + id +
                ", seasonNumber=" + seasonNumber +
                ", chapters=" + chapters +
                '}';
    }

}

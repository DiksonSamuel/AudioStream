package com.example.dellpc.streamaudio;

/**
 * Created by Dell Pc on 27-11-2016.
 */
public class SongDetails {

    private String songtitle;
    private String songartist;

    public SongDetails(String songtitle,String songartist) {

        this.setSongtitle(songtitle);
        this.setSongartist(songartist);
    }

    public String getSongtitle() {
        return songtitle;
    }

    public void setSongtitle(String songtitle) {
        this.songtitle = songtitle;
    }

    public String getSongartist() {
        return songartist;
    }

    public void setSongartist(String songartist) {
        this.songartist = songartist;
    }
}

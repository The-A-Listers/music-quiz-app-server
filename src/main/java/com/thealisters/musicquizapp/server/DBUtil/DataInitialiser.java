package com.thealisters.musicquizapp.server.DBUtil;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.thealisters.musicquizapp.server.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.thealisters.musicquizapp.server.repository.SongRepository;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.List;

@Component
public class DataInitialiser implements CommandLineRunner{

    @Autowired
    private SongRepository songRepository;

    @Override
    public void run(String... args) {

        String tsvFilePath = "../resources/SongsList.tsv";

        if (songRepository.count() == 0) {

            try (CSVReader reader = new CSVReaderBuilder(new FileReader(tsvFilePath))
                    .withCSVParser(new CSVParserBuilder().withSeparator('\t').build())
                    .build()) {
                List<String[]> csvData = reader.readAll();

                for (String[] row : csvData) {
                    Song entity = convertCSVRowToEntity(row);
                    songRepository.save(entity);
                }
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private Song convertCSVRowToEntity(String[] row) {
        if (row.length < 2) {
            throw new IllegalArgumentException("CSV row must contain at least 2 elements (sno, songName)");
        }
        int songRankId = Integer.parseInt(row[0]);
        String songName = row[1];
        Song song = new Song();
        song.setSongRankId(songRankId);
        song.setSongName(songName);
        song.setSongHealth(true);
        return song;
    }
}

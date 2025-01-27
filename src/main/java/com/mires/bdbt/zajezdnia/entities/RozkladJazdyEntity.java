package com.mires.bdbt.zajezdnia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RozkladJazdyEntity {
    private Linia linia;
    private Przystanek przystanek;
    private LiniaPrzystanki liniaPrzystanki;
    private String[] godzinyOdjazdu;

    public RozkladJazdyEntity(Linia linia, Przystanek przystanek, LiniaPrzystanki liniaPrzystanki) {
        this.linia = linia;
        this.przystanek = przystanek;
        this.liniaPrzystanki = liniaPrzystanki;
        this.godzinyOdjazdu = new String[10];
        this.calculateGodzinyOdjazdu();
    }

    private void calculateGodzinyOdjazdu() {
        final Date start = new Calendar.Builder().setDate(2021, 1, 1).setTimeOfDay(8, 0, 0)
                .build().getTime();
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        for (int i = 0; i < 10; i++) {
            this.godzinyOdjazdu[i] = formatter.format(new Date(start.getTime() + (i+1) * liniaPrzystanki.getCzasPrzejazdu().getMinutes() * 60 * 1000 + i * 60 * 60 * 1000));
        }
    }
}

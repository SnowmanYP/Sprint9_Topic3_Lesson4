import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

enum SubtitleLanguage {en,cn,ru}

class SubtitleItem {
    private Map values;
    private LocalTime begin;
    private LocalTime end;

    // геттеры и сеттеры

    public Map getValues() {
        return values;
    }

    public void setValues(Map values) {
        this.values = values;
    }

    public LocalTime getBegin() {
        return begin;
    }

    public void setBegin(LocalTime begin) {
        this.begin = begin;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        // ...
        return false; //--
    }

    @Override
    public int hashCode() {
      // ...
        return 0; //--
    }

    public SubtitleItem(Map values, LocalTime begin, LocalTime end) {
        this.values = values;
        this.begin = begin;
        this.end = end;
    }
}

class SubtitleListTypeToken extends TypeToken<List<SubtitleItem>> //<>??
 { //Здесь ничего писать не нужно
             }
class LocalTimeTypeAdapter extends TypeAdapter<SubtitleItem> //<>??
 {
private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SS");
/*@Override
public void write(???) throws IOException {
}*/
/*@Override
public LocalTime read(???) throws IOException {
}*/

@Override
public void write(JsonWriter out, SubtitleItem value) throws IOException {
     }

@Override
public SubtitleItem read(JsonReader in) throws IOException
{ return null; }
}

public  class Practicum {
    public static void main(String[] args) {
        List<SubtitleItem> subtitles = Arrays.asList(
                new SubtitleItem(Map.of(SubtitleLanguage.ru, "Здравствуйте!",
                        SubtitleLanguage.en, "Hello!",
                        SubtitleLanguage.cn, "Ni hao"),
                        LocalTime.of(0, 0, 15),
                        LocalTime.of(0, 0, 17)
                ),
                new SubtitleItem(Map.of(SubtitleLanguage.ru, "Привет!",
                        SubtitleLanguage.en, "Hi!",
                        SubtitleLanguage.cn, "Ni hao"),
                        LocalTime.of(0, 0, 21),
                        LocalTime.of(0, 0, 24)
                ),
                new SubtitleItem(Map.of(SubtitleLanguage.ru, "Как дела?",
                        SubtitleLanguage.en, "How are you?",
                        SubtitleLanguage.cn, "Ni hao ma"),
                        LocalTime.of(0, 0, 28),
                        LocalTime.of(0, 0, 31)
                ),
                new SubtitleItem(Map.of(SubtitleLanguage.ru, "Всё хорошо, спасибо!",
                        SubtitleLanguage.en, "I'm fine, thank you!",
                        SubtitleLanguage.cn, "Wo hen hao, xie xie"),
                        LocalTime.of(0, 0, 34),
                        LocalTime.of(0, 0, 37)
                )
        );
        // адаптер для преобразования типа LocalTime в String в формате субтитров
        LocalTimeTypeAdapter localTimeTypeAdapter =new LocalTimeTypeAdapter();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalTime.class,localTimeTypeAdapter);
        Gson gson = gsonBuilder.create(); //???+++

        String subtitlesJson = gson.toJson(subtitles.toArray().toString());
        System.out.println(subtitlesJson);

        List<SubtitleItem> parsed = gson.fromJson(subtitlesJson,new SubtitleListTypeToken()); //+++
        if(parsed.equals(subtitles)) {
            System.out.println("Субтитры десериализованы корректно.");
        } else {
            System.out.println("Произошла ошибка при десериализации.");
        }
    }
}
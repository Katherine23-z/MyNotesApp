package com.example.mynoteapplication;


import com.google.firebase.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class NoteMapping {

    public static class Fields {
        public static final String PICTURE = "picture";
        public static final String DATE = "date";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
    }

    public static Note toNote(String id, Map<String, Object> doc) {
        long indexPicture = (long) doc.get(Fields.PICTURE);
        Timestamp timeStamp = (Timestamp) doc.get(Fields.DATE);
        Note answer = new Note((String) doc.get(Fields.TITLE), (String) doc.get(Fields.CONTENT),
                PictureIndexConverter.getPictureByIndex((int) indexPicture), timeStamp.toDate());
        answer.setId(id);
        return answer;
    }

    public static Map<String, Object> toDocument(Note note) {
        Map<String, Object> answer = new HashMap<>();
        answer.put(Fields.TITLE, note.getTitle());
        answer.put(Fields.CONTENT, note.getText());
        answer.put(Fields.PICTURE, PictureIndexConverter.getIndexByPicture(note.getPicture()));
        answer.put(Fields.DATE, note.getDate());
        return answer;
    }
}

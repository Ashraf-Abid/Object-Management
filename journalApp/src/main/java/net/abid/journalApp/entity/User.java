package net.abid.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NonNull
    private String username;
    @Indexed(unique = true)
    @NonNull
    private String password;
    @Id
    private ObjectId objectId;
    private String email;
    private boolean sentimentAnalysis;

    @DBRef
    private List<JournalEntry> journalEntryList = new ArrayList<>();
    private List<String> roles;
}

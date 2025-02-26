package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SUBSCRIBERS_PHOTO", schema = "tm_cim")
public class SubscribersPhoto {
    @Id
    @NotNull
    @Column(name = "PHOTO_ID")
    private String photoId;

    @Column(name = "DATA")
    @Lob
    private byte[] imageData;
}
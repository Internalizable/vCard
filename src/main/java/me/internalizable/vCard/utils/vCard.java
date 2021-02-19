package me.internalizable.vCard.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.internalizable.vCard.utils.objects.Gender;

import java.util.Date;

@Getter
@Setter
@Builder
public class vCard {
    private String name;

    private String familyName;

    private Date birthday;

    private Gender gender;

    private String location;

    private String occupation;
}

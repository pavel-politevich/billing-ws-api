package by.com.lifetech.billingapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentType {
    PASSPORT_RB(Citizenship.RESIDENT),
    IDENTIFICATION_CARD(Citizenship.RESIDENT),
    RESEDENCE_RB(Citizenship.RESIDENT),
    BIOMETRICAL_RESIDENCE_PERMIT(Citizenship.RESIDENT),
    REFUGEE(Citizenship.REFUGEE),
    PASSPORT_RU(Citizenship.NONRESIDENT),
    PASSPORT_ANOTHER(Citizenship.NONRESIDENT),
    PASSPORT_DIPLOMATIC(Citizenship.NONRESIDENT),
    DIPLOMATIC_CARD(Citizenship.NONRESIDENT);

    private Citizenship citizenship;
}

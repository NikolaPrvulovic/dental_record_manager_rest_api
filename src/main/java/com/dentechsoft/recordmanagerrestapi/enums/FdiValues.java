package com.dentechsoft.recordmanagerrestapi.enums;

public enum FdiValues {
    // FIRST QUADRANT
    UPPER_RIGHT_8(18),
    UPPER_RIGHT_7(17),
    UPPER_RIGHT_6(16),
    UPPER_RIGHT_5(15),
    UPPER_RIGHT_4(14),
    UPPER_RIGHT_3(13),
    UPPER_RIGHT_2(12),
    UPPER_RIGHT_1(11),

    // SECOND QUADRANT
    UPPER_LEFT_1(21),
    UPPER_LEFT_2(22),
    UPPER_LEFT_3(23),
    UPPER_LEFT_4(24),
    UPPER_LEFT_5(25),
    UPPER_LEFT_6(26),
    UPPER_LEFT_7(27),
    UPPER_LEFT_8(28),

    // THIRD QUADRANT
    LOWER_LEFT_8(38),
    LOWER_LEFT_7(37),
    LOWER_LEFT_6(36),
    LOWER_LEFT_5(35),
    LOWER_LEFT_4(34),
    LOWER_LEFT_3(33),
    LOWER_LEFT_2(32),
    LOWER_LEFT_1(31),

    // FOURTH QUADRANT
    LOWER_RIGHT_1(41),
    LOWER_RIGHT_2(42),
    LOWER_RIGHT_3(43),
    LOWER_RIGHT_4(44),
    LOWER_RIGHT_5(45),
    LOWER_RIGHT_6(46),
    LOWER_RIGHT_7(47),
    LOWER_RIGHT_8(48);

    private final int value;

    FdiValues(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

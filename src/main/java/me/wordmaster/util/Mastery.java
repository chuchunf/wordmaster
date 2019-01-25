package me.wordmaster.util;

public enum Mastery {
    NEW(0),
    LEARNED(1),
    TEST1(2),
    TEST2(3),
    TEST3(4),
    MASTERED(5);

    private final int level;

    Mastery(int level) {
        this.level = level;
    }

    public static Mastery valueOf(int level) {
        switch (level) {
            case 0:
                return NEW;
            case 1:
                return LEARNED;
            case 2:
                return TEST1;
            case 3:
                return TEST2;
            case 4:
                return TEST3;
            case 5:
                return MASTERED;
            default:
                throw new IllegalArgumentException("invalid level for word mastery [" + level + "]");
        }
    }

    public int getLevel() {
        return level;
    }
}

package com.thoughtful;

public final class PackageSorter {
    private static final double DIMENSION_THRESHOLD = 150.0;
    private static final double VOLUME_THRESHOLD = 1000000.0;
    private static final double MASS_THRESHOLD = 20.0;
    private static final double MAX_REASONABLE_VALUE = 10000.0;


    public String sort(double width, double height, double length, double mass) {
        validateInputs(width, height, length, mass);

        double volume = width * height * length;
        boolean isBulky = width >= DIMENSION_THRESHOLD ||
                height >= DIMENSION_THRESHOLD ||
                length >= DIMENSION_THRESHOLD ||
                volume >= VOLUME_THRESHOLD;
        boolean isHeavy = mass >= MASS_THRESHOLD;

        if (isBulky && isHeavy) {
            return "REJECTED";
        } else if (isBulky || isHeavy) {
            return "SPECIAL";
        } else {
            return "STANDARD";
        }
    }

    private void validateInputs(double width, double height, double length, double mass) {
        validateDimension(width, "width");
        validateDimension(height, "height");
        validateDimension(length, "length");
        validateDimension(mass, "mass");
    }

    private void validateDimension(double value, String name) {
        if (Double.isNaN(value)) {
            throw new IllegalArgumentException(name + " cannot be NaN");
        }
        if (Double.isInfinite(value)) {
            throw new IllegalArgumentException(name + " cannot be infinite");
        }
        if (value <= 0) {
            throw new IllegalArgumentException(String.format("%s must be positive, got: %.2f", name, value));
        }
        if (value > MAX_REASONABLE_VALUE) {
            throw new IllegalArgumentException(String.format("%s is unreasonably large: %.2f", name, value));
        }
    }
}